package IO;

import IO.OutPutPrinter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OutPutPrinterTest {

    @Test
    public void testPrinterWithByteArrayOutputStream() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        OutPutPrinter outPutPrinter = new OutPutPrinter(outputStream);
        outPutPrinter.print("Hello Foo");
        assertThat(outputStream.toString(), is("Hello Foo\n"));
    }

}