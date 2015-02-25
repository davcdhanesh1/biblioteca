import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PrinterTest {

    @Test
    public void testPrinterWithByteArrayOutputStream() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(outputStream);
        printer.print("Hello Foo");
        assertThat(outputStream.toString(),is("Hello Foo"));
    }

    @Test
    public void testPrinterWithSystemOutPrintStream() throws Exception {
        Printer printer = new Printer(System.out);
        printer.print("Hello Foo");

    }
}