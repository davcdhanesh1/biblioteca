package IO;

import java.io.OutputStream;
import java.io.PrintStream;

public class OutPutPrinter {
    private final PrintStream printStream;

    public OutPutPrinter(OutputStream outputStream) {
        this.printStream = new PrintStream(outputStream);
    }

    public void print(String message) {
        printStream.println(message);
    }
}
