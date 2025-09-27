package select;

import metrics.Metrics;
import utils.ArrayUtils;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        metrics.start();
        int result = selectRecursive(arr, 0, arr.length - 1, k, metrics);
        metrics.stop();
        return result;
    }

    private static int selectRecursive(int[] arr, int left, int right, int k, Metrics metrics) {
        metrics.enterRecursion();

        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right, metrics);
        int pivotIndex = ArrayUtils.partition(arr, left, right, pivot, metrics);

        if (k == pivotIndex) {
            metrics.exitRecursion();
            return arr[k];
        } else if (k < pivotIndex) {
            int result = selectRecursive(arr, left, pivotIndex - 1, k, metrics);
            metrics.exitRecursion();
            return result;
        } else {
            int result = selectRecursive(arr, pivotIndex + 1, right, k, metrics);
            metrics.exitRecursion();
            return result;
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        int numMedians = (int) Math.ceil((double) n / 5.0);

        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        if (numMedians == 1) {
            return medians[0];
        } else {
            return medianOfMedians(medians, 0, medians.length - 1, metrics);
        }
    }
}