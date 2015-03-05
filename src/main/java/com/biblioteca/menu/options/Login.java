package com.biblioteca.menu.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;

import java.util.Scanner;

public class Login extends MenuOption {
    public Login() {
        super("Login");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        if (userSession.getCurrentUser() != null) return;
        userSession.login();
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
