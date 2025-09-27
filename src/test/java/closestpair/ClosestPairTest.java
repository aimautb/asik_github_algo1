package closestpair;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testThreePoints() {
        Point[] pts = { new Point(0, 0), new Point(3, 4), new Point(1, 1) };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testIdenticalPoints() {
        Point[] pts = { new Point(2, 2), new Point(2, 2), new Point(5, 5) };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(0.0, d, 1e-9);
    }

    @Test
    void testPointsOnLine() {
        Point[] pts = {
                new Point(0, 0),
                new Point(5, 0),
                new Point(2, 0),
                new Point(8, 0)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertEquals(2.0, d, 1e-9);
    }

    @Test
    void testRandomPoints() {
        Random rand = new Random();
        Point[] pts = new Point[100];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(pts, m);
        assertTrue(d >= 0.0);
    }
}