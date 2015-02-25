import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {
    private static final String WELCOME_TO_BIBLIOTECA_MSG = "Welcome To Biblioteca";

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(outputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run(printer);
        assertThat(outputStream.toString(),is(WELCOME_TO_BIBLIOTECA_MSG));
    }

}