import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(outputStream);
        Main.run(printer);
        assertThat(outputStream.toString(),is(Main.WELCOME_TO_BIBLIOTECA_MSG));
    }
}