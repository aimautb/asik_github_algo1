package quicksort;

import metrics.Metrics;
import sort.SortingAlgorithm;
import utils.ArrayUtils;

import java.util.Random;

public class QuickSort implements SortingAlgorithm {

    private static final Random rand = new Random();

    @Override
    public void sort(int[] arr, Metrics metrics) {
        metrics.start();
        quickSort(arr, 0, arr.length - 1, metrics);
        metrics.stop();
    }

    @Override
    public String name() {
        return "quicksort";
    }

    private void quickSort(int[] arr, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivotValue = arr[pivotIndex];

            int index = ArrayUtils.partition(arr, left, right, pivotValue, metrics);

            if (index - left < right - index) {
                quickSort(arr, left, index - 1, metrics);
                left = index + 1;
            } else {
                quickSort(arr, index + 1, right, metrics);
                right = index - 1;
            }
        }

        metrics.exitRecursion();
    }
}