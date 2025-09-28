package cli;

import java.io.FileWriter;
import java.io.IOException;

public interface AlgorithmRunner {
    void run(int size, int trials, FileWriter csv) throws IOException;
}