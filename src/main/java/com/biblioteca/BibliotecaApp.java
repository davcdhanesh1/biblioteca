package com.biblioteca;

import com.biblioteca.book.BookCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
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

    public void run(Library library) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned, InputValidationException {
        init();
        Menu menu; String option;
        scanner.useDelimiter("\n");
        while(scanner.hasNext()) {
            option = scanner.next();
            if (!isValidInput(option)) continue;
            menu = menuList.find(option);
            performSelectedMenu(library, menu);
            if(!menu.shouldContinueRunning()) {
                break;
            }
            printMenuListAndPrompt();
        }

    }

    private boolean isValidInput(String option) {
        try {
            Validator.validate(option);
        } catch (InputValidationException e) {
            printErrorMessage(e.getMessage());
            printMenuListAndPrompt();
            return false;
        }
        return true;
    }

    private void printErrorMessage(String msg) {
        printSeparatorLine();
        printer.println(msg);
        printSeparatorLine();
    }

    private void performSelectedMenu(Library library, Menu menu) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned, InputValidationException {
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
