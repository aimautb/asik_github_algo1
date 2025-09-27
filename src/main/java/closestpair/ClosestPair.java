package closestpair;

import metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points, Metrics metrics) {
        metrics.start();
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];
        double result = closestRecursive(sortedByX, aux, 0, points.length - 1, metrics);
        metrics.stop();
        return result;
    }

    private static double closestRecursive(Point[] pts, Point[] aux, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        if (right - left <= 3) {
            double minDist = bruteForce(pts, left, right, metrics);
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            metrics.exitRecursion();
            return minDist;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;

        double d1 = closestRecursive(pts, aux, left, mid, metrics);
        double d2 = closestRecursive(pts, aux, mid + 1, right, metrics);
        double d = Math.min(d1, d2);


        mergeByY(pts, aux, left, mid, right);


        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripSize++] = pts[i];
            }
        }


        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                metrics.inComparisons();
                double dist = dist(aux[i], aux[j]);
                if (dist < d) {
                    d = dist;
                }
            }
        }

        metrics.exitRecursion();
        return d;
    }

    private static double bruteForce(Point[] pts, int left, int right, Metrics metrics) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.inComparisons();
                double d = dist(pts[i], pts[j]);
                if (d < min) {
                    min = d;
                }
            }
        }
        return min;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) {
                aux[k++] = pts[i++];
            } else {
                aux[k++] = pts[j++];
            }
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        for (int p = left; p <= right; p++) {
            pts[p] = aux[p];
        }
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}