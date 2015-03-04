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
    private final Library library;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;
    private MenuList menuList;

    public BibliotecaApp(Printer printer, Scanner scanner, MenuList menuList, UserList userList, Library library) {

        this.printer = printer;
        this.scanner = scanner;
        this.menuList = menuList;
        this.userList = userList;
        this.userSession = UserSession.createNew(userList, printer, scanner);
        this.library = library;
    }

    public void run() throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        init();
        startRoutingEngine();
    }

    private void startRoutingEngine() throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        Menu menu;
        String option;

        while(isAppRunning()) {
            option = readInput();
            if (inputIsNotValid(option)) continue;

            menu = getSelectedMenu(option);
            executeSelectedMenuOption(library, menu);
            if(selectedMenuIsQuit(menu)) break;

            printMenuListAndPrompt();
        }
    }

    private boolean selectedMenuIsQuit(Menu menu) {
        return !menu.shouldContinueRunning();
    }

    private Menu getSelectedMenu(String option) {
        return menuList.find(option);
    }

    private String readInput() {
        String option;
        option = scanner.next();
        return option;
    }

    private boolean isAppRunning() {
        return scanner.hasNext();
    }

    private void init() {
        scanner.useDelimiter("\n");
        printWelcomeMessage();
        printSeparatorLine();
        printMenuListAndPrompt();
    }

    private boolean inputIsNotValid(String option) {
        try {
            Validator.validate(option);
            return false;
        } catch (InputValidationException e) {
            printErrorMessage(e.getMessage());
            printMenuListAndPrompt();
            return true;
        }
    }

    void printErrorMessage(String msg) {
        printSeparatorLine();
        printer.println(msg);
        printSeparatorLine();
    }

    private void executeSelectedMenuOption(Library library, Menu menu) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        printSeparatorLine();

        if( menu.isSecureLoginRequired() ) {
            try {
                userSession.login();
            } catch (InvalidLibraryAndPasswordCombination e) {
                printer.println(e.getMessage());
                printSeparatorLine();
                return;
            }
        }
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
