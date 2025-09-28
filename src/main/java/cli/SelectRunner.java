package cli;

import select.DeterministicSelect;
import metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SelectRunner implements AlgorithmRunner {
    @Override
    public void run(int size, int trials, FileWriter csv) throws IOException {
        Random rand = new Random();
        for (int t = 1; t <= trials; t++) {
            int[] arr = randomArray(size);
            int k = rand.nextInt(size);
            Metrics m = new Metrics();
            DeterministicSelect.select(arr, k, m);
            csv.write(String.format("select,%d,%d,%d,%d,%d,%d\n",
                    size, t, m.getTimeMillis(),
                    m.getComparisons(), m.getOperations(), m.getMaxDepth()));
        }
    }

    private int[] randomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = (int) (Math.random() * 100000);
        return arr;
    }
}