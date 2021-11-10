package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        System.out.println("Available processor on the system: " + Runtime.getRuntime().availableProcessors());

        Random random = new Random();


        ArrayList<Long> timeList = new ArrayList<>();
        for (int threadCount = 2; threadCount <=64; threadCount =threadCount*2) {
            ForkJoinPool f = new ForkJoinPool(threadCount);
            //System.out.println("Using " + f + "threads: ");
            long meanTime = 0;
            long minTime = 10000000;
            long optimalCutOff = 10000000;
            for (int arraySize = 1000000; arraySize <= 4000000; arraySize += 1000000) {
                int[] array = new int[arraySize];

                for (int j = 50; j < 100; j = j + 5) {
                    ParSort.cutoff = 10000 * (j + 1);
                    //for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                    long time;
                    long startTime = System.currentTimeMillis();
                    for (int t = 0; t < 20; t++) {
                        for (int i = 0; i < array.length; i++)
                            array[i] = random.nextInt(10000000);
                        ParSort.sort(array, 0, array.length, f);
                    }
                    long endTime = System.currentTimeMillis();
                    time = (endTime - startTime);
                    timeList.add(time);

                    meanTime += time;
                    if (time < minTime) {
                        minTime = time;
                        optimalCutOff = ParSort.cutoff;
                    }
                    //System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t20times Time:" + time + "ms");
                    //System.out.println(time);
                }
                meanTime /= 20;
                System.out.println("For Array length of :" + arraySize + ","+ threadCount + " threads, meanTime: " + meanTime + " minTime: " + minTime + " optimal cutoff " + optimalCutOff);


            }
        }

        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 0;
            for (long i : timeList) {
                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
                j++;
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
