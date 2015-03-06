package com.biblioteca.view.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.view.menu.options.ListAllBook;
import com.biblioteca.view.menu.options.MenuOption;
import com.biblioteca.view.menu.options.Quit;
import testhelpers.StringUtil;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuOptionListTest {

    @Test
    public void testPrintAll() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new Quit());
        String expectedMenuList = StringUtil.getOutputString("1. List Books", "2. Quit");
        menuOptionList.printAll(printer);

        assertThat(byteArrayOutputStream.toString(),is(expectedMenuList));
    }

    @Test
    public void testFindMenu() throws Exception {
        MenuOptionList menuOptionList = new MenuOptionList();
        MenuOption listAllBookOption = new ListAllBook();
        menuOptionList.add(listAllBookOption);
        MenuOption quitOption = new Quit();
        menuOptionList.add(quitOption);

        assertThat(menuOptionList.find("1"), is(listAllBookOption));
    }

    @Test
    public void testFindMenuWhenInvalidOptionIsGiven() throws Exception {
        MenuOptionList menuOptionList = new MenuOptionList();
        MenuOption listAllBookOption = new ListAllBook();
        menuOptionList.add(listAllBookOption);
        MenuOption quitOption = new Quit();
        menuOptionList.add(quitOption);

        assertThat(menuOptionList.find("3").toString(), is("Invalid Option!"));
    }

    @Test
    public void testRemove() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        MenuOptionList menuOptionList = new MenuOptionList();
        MenuOption listAllBookOption = new ListAllBook();
        menuOptionList.add(listAllBookOption);
        MenuOption quitOption = new Quit();
        menuOptionList.add(quitOption);

        menuOptionList.remove("List Books");
        menuOptionList.printAll(printer);

        assertThat(byteArrayOutputStream.toString(), is("1. Quit\n"));
    }

    @Test
    public void testHasMenu() throws Exception {
        MenuOptionList menuOptionList = new MenuOptionList();
        MenuOption listAllBookOption = new ListAllBook();
        menuOptionList.add(listAllBookOption);
        MenuOption quitOption = new Quit();
        menuOptionList.add(quitOption);
        menuOptionList.remove("List Books");

        assertThat(menuOptionList.hasMenu("List Books"), is(false));
        assertThat(menuOptionList.hasMenu("Quit"), is(true));
    }
}