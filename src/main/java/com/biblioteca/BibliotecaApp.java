package com.biblioteca;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.Library;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.MenuOptionList;
import com.biblioteca.view.menuOptions.Login;
import com.biblioteca.view.menuOptions.MenuOption;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.model.UserList;
import com.biblioteca.view.ViewState;

import java.util.Scanner;

public class BibliotecaApp {

    private final UserSession userSession;
    private final Library library;
    private final View view;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;
    private MenuOptionList menuOptionList;

    public BibliotecaApp(Printer printer, Scanner scanner, UserList userList, Library library) {

        this.view = new View(printer, scanner);
        this.printer = printer;
        this.scanner = scanner;
        this.userList = userList;
        this.userSession = UserSession.createNew(userList);
        this.library = library;
        this.menuOptionList = ViewState.getCurrentMenuList(userSession).menuOptionList;
    }

    public void run() throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        init();
        String option;
        MenuOption menuOption;

        while(isAppRunning()) {
            option = readInput();
            if (inputIsNotValid(option)) continue;

            printSeparatorLine();
            menuOption = getSelectedMenuOption(option);
            performSelectedMenuOption(library, menuOption);
            printSeparatorLine();
            if(isSelectedMenuOptionIsQuit(menuOption)) break;

            printMenuListAndPrompt();
        }
    }

    private MenuOption getSelectedMenuOption(String option) {
        return menuOptionList.find(option);
    }

    private void performSelectedMenuOption(Library library, MenuOption menuOption) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        String output;
        try {
            if (givenMenuOptionRequiresLogin(menuOption)) login();
            output = menuOption.perform(userSession, library, view);
        } catch (InvalidLibraryAndPasswordCombination e) {
            output = e.getMessage();
        }
        view.render(output);
    }

    private boolean isSelectedMenuOptionIsQuit(MenuOption menuOption) {
        return !menuOption.shouldContinueRunning();
    }

    private String readInput() {
        String option;
        option = view.scan();
        return option;
    }

    private boolean isAppRunning() {
        return view.hasInput();
    }

    private void init() {
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

    private boolean givenMenuOptionRequiresLogin(MenuOption menuOption) {
        return menuOption.isSecureLoginRequired();
    }

    private void login() throws InvalidLibraryAndPasswordCombination, ItemIsNotAvailableForCheckOut, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        if (userSession.getCurrentUser() != null) return;
        Login login = new Login();
        login.perform(userSession, library, view);
        printSeparatorLine();
    }

    private void printErrorMessage(String msg) {
        printSeparatorLine();
        view.render(msg);
        printSeparatorLine();
    }

    private void printMenuListAndPrompt() {
        printMenuList();
        printPrompt();
    }

    private void printMenuList() {
        ViewState viewState = ViewState.getCurrentMenuList(userSession);
        menuOptionList = viewState.menuOptionList;
        view.render(menuOptionList.getAll());
    }

    private void printPrompt() {
        view.render("Select Option: ");
    }

    private void printSeparatorLine() {
        view.render("-----------------------------------------------------------------------------");
    }

    private void printWelcomeMessage() {
        view.render("Welcome To Biblioteca");
    }
}
