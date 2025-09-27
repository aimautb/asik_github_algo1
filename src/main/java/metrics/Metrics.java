package metrics;

public class Metrics {
    private long comparisons;
    private long operations;
    private long currentDepth;
    private long maxDepth;
    private long startTime;
    private long endTime;

    public void start(){
        comparisons = 0;
        operations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void inComparisons() {
        comparisons++;
    }

    public void inOperations() {
        operations++;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getOperations() {
        return operations;
    }

    public long getMaxDepth() {
        return maxDepth;
    }

    public long getTimeMillis() {
        return (endTime - startTime) / 1000000;
    }
}