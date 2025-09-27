package mergesort;

import metrics.Metrics;
import sort.SortingAlgorithm;

public class MergeSort implements SortingAlgorithm {

    private static final int CUTOFF = 16; // порог для insertion sort

    @Override
    public void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length];
        metrics.start();
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
        metrics.stop();
    }

    @Override
    public String name() {
        return "mergesort";
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid, metrics);
        mergeSort(arr, buffer, mid + 1, right, metrics);
        merge(arr, buffer, left, mid, right, metrics);

        metrics.exitRecursion();
    }

    private void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.inComparisons();
                arr[j + 1] = arr[j];
                metrics.inOperations();
                j--;
            }
            arr[j + 1] = key;
            metrics.inOperations();
        }
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.inComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
            metrics.inOperations();
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int p = left; p <= right; p++) arr[p] = buffer[p];
    }
}