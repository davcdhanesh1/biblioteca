package com.biblioteca;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.menu.MenuOptionList;
import com.biblioteca.menu.options.Login;
import com.biblioteca.menu.options.MenuOption;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.UserList;
import com.biblioteca.view.ViewState;

import java.util.Scanner;

public class BibliotecaApp {

    private final UserSession userSession;
    private final Library library;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;
    private MenuOptionList menuOptionList;

    public BibliotecaApp(Printer printer, Scanner scanner, UserList userList, Library library) {

        this.printer = printer;
        this.scanner = scanner;
        this.userList = userList;
        this.userSession = UserSession.createNew(userList, printer, scanner);
        this.library = library;
        this.menuOptionList = ViewState.getCurrentView(userSession).menuOptionList;
    }

    public void run() throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        init();
        startRoutingEngine();
    }

    private void startRoutingEngine() throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        MenuOption menuOption;
        String option;

        while(isAppRunning()) {
            option = readInput();
            if (inputIsNotValid(option)) continue;

            menuOption = getSelectedMenuOption(option);
            executeSelectedMenuOption(library, menuOption);
            if(isSelectedMenuOptionIsQuit(menuOption)) break;

            printMenuListAndPrompt();
        }
    }

    private boolean isSelectedMenuOptionIsQuit(MenuOption menuOption) {
        return !menuOption.shouldContinueRunning();
    }

    private MenuOption getSelectedMenuOption(String option) {
        return menuOptionList.find(option);
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

    private void printErrorMessage(String msg) {
        printSeparatorLine();
        printer.println(msg);
        printSeparatorLine();
    }

    private void executeSelectedMenuOption(Library library, MenuOption menuOption) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        printSeparatorLine();
        try {
            if (givenMenuOptionRequiresLogin(menuOption)) login();
            menuOption.perform(userSession, library, printer, scanner);
        } catch (InvalidLibraryAndPasswordCombination e) {
            printErrorMessage(e.getMessage());
            return;
        }
        printSeparatorLine();
    }

    private boolean givenMenuOptionRequiresLogin(MenuOption menuOption) {
        return menuOption.isSecureLoginRequired();
    }

    private void login() throws InvalidLibraryAndPasswordCombination, ItemIsNotAvailableForCheckOut, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        if (userSession.getCurrentUser() != null) return;
        Login login = new Login();
        login.perform(userSession, library, printer, scanner);
        printSeparatorLine();
    }

    private void printMenuListAndPrompt() {
        printMenuList();
        printPrompt();
    }

    private void printMenuList() {
        ViewState viewState = ViewState.getCurrentView(userSession);
        menuOptionList = viewState.menuOptionList;
        menuOptionList.printAll(printer);
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
