import IO.OutPutPrinter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void testPrintAll() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = new PrintStream(byteArrayOutputStream);
        OutPutPrinter outPutPrinter = new OutPutPrinter(outputStream);
        Menu.printAll(outPutPrinter);

        String expectedListOfAllMenus = new String();
        expectedListOfAllMenus +="1. List Books\n";
        assertThat(byteArrayOutputStream.toString(), is(expectedListOfAllMenus));
    }
}