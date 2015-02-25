import java.io.OutputStream;
import java.io.PrintStream;

public class Printer {
    private final PrintStream printStream;

    public Printer(OutputStream outputStream) {
        this.printStream = new PrintStream(outputStream);
    }

    public void print(String message) {
        printStream.println(message);
    }
}
