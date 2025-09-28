package cli;

import closestpair.ClosestPair;
import closestpair.Point;
import metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;

public class ClosestPairRunner implements AlgorithmRunner {
    @Override
    public void run(int size, int trials, FileWriter csv) throws IOException {
        for (int t = 1; t <= trials; t++) {
            Point[] pts = randomPoints(size);
            Metrics m = new Metrics();
            ClosestPair.findClosest(pts, m);
            csv.write(String.format("closest,%d,%d,%d,%d,%d,%d\n",
                    size, t, m.getTimeMillis(),
                    m.getComparisons(), m.getOperations(), m.getMaxDepth()));
        }
    }

    private Point[] randomPoints(int size) {
        Point[] pts = new Point[size];
        for (int i = 0; i < size; i++) {
            pts[i] = new Point(Math.random() * 10000, Math.random() * 10000);
        }
        return pts;
    }
}