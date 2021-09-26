package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

class Assignment2 {
    public static Config config;
    public static int N = 1000;
    private static Object Supplier;

    public static void main(String args[]) {
        /*
        //random array elements
        System.out.print("Benchmarking InsertionSort for random array elements:\n");
        for (int i = 0; i < 5; i++) {
            Helper<Integer> helper = new BaseHelper<Integer>("Insertion Sort", N, config);
            Supplier<Integer[]> supplier = () -> helper.random(Integer.class, Random::nextInt);
            Assignment2.execBenchmark(helper, supplier);
            N = N * 2;
        }
        */

        //ascending order sorted array
        System.out.print("Benchmarking InsertionSort for sorted array elements:\n");
        for (int i = 0; i < 5; i++) {
            Helper<Integer> helper = new BaseHelper<Integer>("Insertion Sort", N, config);
            Supplier<Integer[]> supplier = () -> populateOrderedArray(N);
            Assignment2.execBenchmark(helper, supplier);
            N = N * 2;
        }

        /*
        //partially sorted array
        System.out.print("Benchmarking InsertionSort for partially sorted array elements:\n");
        for (int i = 0; i < 5; i++) {
            Helper<Integer> helper = new BaseHelper<Integer>("Insertion Sort", N, config);
            Supplier<Integer[]> supplier = () -> populatePartiallyOrderedArray(N);
            Assignment2.execBenchmark(helper, supplier);
            N = N * 2;
        }
        */


        /*
        //reverse ordered array elements
        System.out.print("Benchmarking InsertionSort for reverse sorted array elements:\n");
        for (int i = 0; i < 5; i++) {
            Helper<Integer> helper = new BaseHelper<Integer>("Insertion Sort", N, config);
            Supplier<Integer[]> supplier = () -> populateReverseOrderedArray(N);
            Assignment2.execBenchmark(helper, supplier);
            N = N * 2;
        }
        */
    }

    private static void execBenchmark(Helper helper, Supplier supplier) {
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>("Insertion sort for " + N + " Integers",
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::sort,
                null
        );
        System.out.println((benchmark.runFromSupplier(supplier, 100)) + "ms");
    }

    private static Integer[] populateOrderedArray(int length){
        Integer[] orderedArray = new Integer[length];
        for (int i = 0; i < length; i++){
            orderedArray[i] = i + 1;
        }
        return orderedArray;
    }

    private static Integer[] populateReverseOrderedArray(int length){
        Integer[] reverseOrderedArray = new Integer[length];
        for (int i = 0; i < length; i++){
            reverseOrderedArray[i] = length - i + 1;
        }
        return reverseOrderedArray;
    }

    private static Integer[] populatePartiallyOrderedArray(int length){
        Integer[] partiallyOrderedArray = new Integer[length];
        for (int i = 0; i < length; i++){
            if(i <= 300)
                partiallyOrderedArray[i] = i + 1;
            else
                partiallyOrderedArray[i] = length - i + 1;
        }
        return partiallyOrderedArray;
    }

}