package com.biblioteca;

import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.menu.*;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.UserList;

import java.util.Scanner;

public class BibliotecaApp {

    private final UserSession userSession;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;
    private MenuList menuList;

    public BibliotecaApp(Printer printer, Scanner scanner, MenuList menuList, UserList userList) {

        this.printer = printer;
        this.scanner = scanner;
        this.menuList = menuList;
        this.userList = userList;
        this.userSession = UserSession.createNew(userList, printer, scanner);
    }

    public void run(Library library) throws
            InvalidItemException,
            ItemIsNotAvailableForCheckOut,
            ItemCanNotBeReturned,
            InputValidationException,
            InvalidLibraryAndPasswordCombination {

        init();
        Menu menu; String option;
        scanner.useDelimiter("\n");
        while(scanner.hasNext()) {
            option = scanner.next();
            if (!isValidInput(option)) continue;

            menu = menuList.find(option);
            performSelectedMenu(library, menu);
            if(!menu.shouldContinueRunning()) break;

            printMenuListAndPrompt();
        }

    }

    private void init() {
        printWelcomeMessage();
        printSeparatorLine();
        printMenuListAndPrompt();
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

    void printErrorMessage(String msg) {
        printSeparatorLine();
        printer.println(msg);
        printSeparatorLine();
    }

    private void performSelectedMenu(Library library, Menu menu) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        printSeparatorLine();
        menu.perform(userSession, library, printer, scanner);
        printSeparatorLine();
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
