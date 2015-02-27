package com.biblioteca;

import com.biblioteca.book.BookCanNotBeReturned;
import com.biblioteca.io.Printer;
import com.biblioteca.book.BookIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.book.BookNotFoundException;
import com.biblioteca.menu.*;

import java.util.Scanner;

public class BibliotecaApp {

    private Printer printer;
    private Scanner scanner;
    private MenuList menuList;

    public BibliotecaApp(Printer printer, Scanner scanner, MenuList menuList) {

        this.printer = printer;
        this.scanner = scanner;
        this.menuList = menuList;
    }

    public void run(Library library) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned {
        init();
        Menu menu; String option;
        scanner.useDelimiter("\n");
        while(scanner.hasNext()) {
            option = scanner.next();
            menu = menuList.find(option);
            performSelectedMenu(library, menu);
            if(!menu.shouldContinueRunning()) {
                break;
            }
            printMenuListAndPrompt();
        }
    }

    private void performSelectedMenu(Library library, Menu menu) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned {
        printSeparatorLine();
        menu.perform(library, printer, scanner);
        printSeparatorLine();
    }

    private void init() {
        printWelcomeMessage();
        printSeparatorLine();
        printMenuListAndPrompt();
    }

    private void printMenuListAndPrompt() {
        printMenuList();
        printPrompt();
    }

    private void printMenuList() {
        menuList.printAll(printer);
    }

    private void printPrompt() {
        printer.println("Select Option: ");
    }

    private void printSeparatorLine() {
        printer.println("-----------------------------------------------------------------------------");
    }

    private void printWelcomeMessage() {
        printer.println("Welcome To Biblioteca");
    }
}
