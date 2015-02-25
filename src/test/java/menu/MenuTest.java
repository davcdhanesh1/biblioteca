package menu;

import IO.Printer;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuTest {

    ByteArrayOutputStream byteArrayOutputStream;
    Printer printer;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);
    }

    @Test
    public void testForOptionForListAllBookOption() throws Exception {
        assertThat(Menu.forOption("1"), is(Menu.ListAllBook));
    }

    @Test
    public void testForOptionForInvalidOption() throws Exception {
        assertThat(Menu.forOption("-1"), is(Menu.InvalidOption));
    }

    @Test
    public void testPrintAll() throws Exception {
        Menu.printAll(printer);

        assertThat(byteArrayOutputStream.toString(),is("1. List Books\n\n"));
    }
}