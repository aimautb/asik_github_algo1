package utils;

import metrics.Metrics;
import java.util.Random;

public class ArrayUtils {

    private static final Random rand = new Random();

    public static void swap(int[] arr, int i, int j, Metrics metrics) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        if (metrics != null) {
            metrics.inOperations();
        }
    }

    public static void shuffle(int[] arr, Metrics metrics) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j, metrics);
        }
    }


    public static int partition(int[] arr, int left, int right, int pivotValue, Metrics metrics) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right, metrics);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) metrics.inComparisons();
            if (arr[i] <= pivotValue) {
                swap(arr, i, storeIndex, metrics);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right, metrics);
        return storeIndex;
    }


    public static boolean isNullOrEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }
}