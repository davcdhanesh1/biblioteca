package menu;

import IO.Printer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuListTest {
    @Test
    public void testPrintAll() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);
        MenuList menuList = new MenuList();
        menuList.add(new ListAllBook());
        String expectedMenuList = "1. List Books\n";
        menuList.printAll(printer);

        assertThat(byteArrayOutputStream.toString(),is(expectedMenuList));
    }
}