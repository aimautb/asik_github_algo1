package sort;

import metrics.Metrics;

public interface SortingAlgorithm {
    void sort(int[] arr, Metrics metrics);
    String name();
}