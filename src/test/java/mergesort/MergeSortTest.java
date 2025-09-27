package mergesort;

import metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 3, 8, 1, 2};
        Metrics m = new Metrics();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
        assertTrue(m.getComparisons() > 0);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        Metrics m = new Metrics();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        Random rand = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000);
        }

        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        Metrics m = new Metrics();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, m);

        assertArrayEquals(copy, arr);
        assertTrue(m.getMaxDepth() > 0);
    }
}
