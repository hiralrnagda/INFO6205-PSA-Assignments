package edu.neu.coe.info6205.union_find;
import java.util.Random;

public class UF_Client
{
    //generates random pairs of integers between 0 and n-1, calling connected() to determine if they are connected and union() if not.
    public static int count(int n)
    {
        UF_HWQUPC ufhwqupc = new UF_HWQUPC(n);

        int count = 0;
        Random random = new Random();
        while (ufhwqupc.components() > 1){
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            if(!ufhwqupc.connected(i, j)){
                ufhwqupc.union(i,j);
            }
            count++;
        }
        return count;
    }

    public static void main(String args[]){
        // Loop until all sites are connected then print the number of connections generated
        for(int i = 1000; i <= 20000; i+=1000)
        {
            System.out.println("For " + i + "sites: " + count(i) + "number of random connections required for all sites to be fully connected");
        }
    }
}
