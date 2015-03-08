package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.view.View;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MenuOptionListTest {

    @Test
    public void testPrintAll() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new Quit());
        String expectedMenuList = StringUtil.getOutputString("1. List Books", "2. Quit");
        String allMenus = menuOptionList.getAll();

        View view = new View(printer, scanner);
        view.render(allMenus);

        assertEquals(expectedMenuList, byteArrayOutputStream.toString());
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
        Scanner scanner = new Scanner(System.in);
        View view = new View(printer, scanner);
        view.render(menuOptionList.getAll());

        assertThat(byteArrayOutputStream.toString(), is("1. Quit\n"));
    }

}