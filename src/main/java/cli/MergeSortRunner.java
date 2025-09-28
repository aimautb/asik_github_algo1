package cli;

import mergesort.MergeSort;
import metrics.Metrics;
import sort.SortingAlgorithm;

import java.io.FileWriter;
import java.io.IOException;

public class MergeSortRunner implements AlgorithmRunner {
    @Override
    public void run(int size, int trials, FileWriter csv) throws IOException {
        SortingAlgorithm algo = new MergeSort();
        for (int t = 1; t <= trials; t++) {
            int[] arr = randomArray(size);
            Metrics m = new Metrics();
            algo.sort(arr, m);
            csv.write(String.format("%s,%d,%d,%d,%d,%d,%d\n",
                    algo.name(), size, t, m.getTimeMillis(),
                    m.getComparisons(), m.getOperations(), m.getMaxDepth()));
        }
    }

    private int[] randomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = (int) (Math.random() * 100000);
        return arr;
    }
}