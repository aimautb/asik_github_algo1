package cli;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        int size = 10000;
        int trials = 5;


        long mergeSortTime = 0;
        long quickSortTime = 0;

        try (FileWriter csv = new FileWriter("results.csv")) {

            csv.write("algo,size,trial,time(ms),comparisons,operations,maxDepth\n");


            mergeSortTime = runAndMeasure(new MergeSortRunner(), size, trials, csv);


            quickSortTime = runAndMeasure(new QuickSortRunner(), size, trials, csv);


            runAndMeasure(new SelectRunner(), size, trials, csv);


            runAndMeasure(new ClosestPairRunner(), size, trials, csv);
        }


        System.out.println("\n=== Analysis ===");
        if (mergeSortTime < quickSortTime) {
            System.out.printf("✅ MergeSort is faster on average (%.2f ms vs %.2f ms)%n",
                    (double) mergeSortTime / trials, (double) quickSortTime / trials);
        } else if (quickSortTime < mergeSortTime) {
            System.out.printf("✅ QuickSort is faster on average (%.2f ms vs %.2f ms)%n",
                    (double) quickSortTime / trials, (double) mergeSortTime / trials);
        } else {
            System.out.println("⚖️ Both MergeSort and QuickSort performed equally.");
        }
    }

    private static long runAndMeasure(AlgorithmRunner runner, int size, int trials, FileWriter csv) throws IOException {
        long totalTime = 0;
        for (int t = 1; t <= trials; t++) {
            long start = System.nanoTime();
            runner.run(size, 1, csv);
            long end = System.nanoTime();
            totalTime += (end - start) / 1_000_000;
        }
        return totalTime;
    }
}