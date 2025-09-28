package bench;

import select.DeterministicSelect;
import java.util.Arrays;
import java.util.Random;

public class SelectVsSortBenchmark {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        Random rand = new Random();

        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 1_000_000).toArray();


            int[] copy1 = Arrays.copyOf(arr, arr.length);
            long t1 = System.nanoTime();
            int k = copy1.length / 2;
            DeterministicSelect.select(copy1, k, null);
            long t2 = System.nanoTime();


            int[] copy2 = Arrays.copyOf(arr, arr.length);
            long t3 = System.nanoTime();
            Arrays.sort(copy2);
            int median = copy2[copy2.length / 2];
            long t4 = System.nanoTime();

            System.out.printf("n=%d | Select=%.3f ms | Sort=%.3f ms (median=%d)%n",
                    n, (t2 - t1) / 1e6, (t4 - t3) / 1e6, median);
        }
    }
}