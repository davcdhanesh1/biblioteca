package com.biblioteca.view.menuOptions;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.view.View;

import java.util.Scanner;

public class Login extends MenuOption {
    public Login() {
        super("Login");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        if (userSession.getCurrentUser() == null) {
            userSession.login(printer, scanner);
        }
        return new View("Successfully logged in.", printer, scanner);
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public boolean isSecureLoginRequired() {
        // This is login option itself, hence false by default !
        return false;
    }
}
