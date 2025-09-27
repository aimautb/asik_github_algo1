package metrics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MetricsTest {

    @Test
    void testMetricsCounting() {
        Metrics m = new Metrics();
        m.start();

        m.inComparisons();
        m.inComparisons();
        m.inOperations();

        m.enterRecursion();
        m.enterRecursion();
        m.exitRecursion();

        m.stop();

        assertEquals(2, m.getComparisons());
        assertEquals(1, m.getOperations());
        assertEquals(2, m.getMaxDepth());
        assertTrue(m.getTimeMillis() >= 0);
    }
}