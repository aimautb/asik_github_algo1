package select;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 1, 6, 3};
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(arr, 2, m); // 3-й по порядку элемент
        assertEquals(3, val);
    }

    @Test
    void testMinElement() {
        int[] arr = {10, 4, 7, 1, 8};
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(arr, 0, m); // минимальный
        assertEquals(1, val);
    }

    @Test
    void testMaxElement() {
        int[] arr = {10, 4, 7, 1, 8};
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(arr, arr.length - 1, m); // максимальный
        assertEquals(10, val);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {5, 1, 3, 5, 2, 5, 4};
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(arr, 3, m); // 4-й элемент
        Arrays.sort(arr);
        assertEquals(arr[3], val);
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int[] arr = new int[50];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rand.nextInt(1000);
            }
            int k = rand.nextInt(arr.length);

            int[] copy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(copy);

            Metrics m = new Metrics();
            int val = DeterministicSelect.select(arr, k, m);

            assertEquals(copy[k], val);
        }
    }
}