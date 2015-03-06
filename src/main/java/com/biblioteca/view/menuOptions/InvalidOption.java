package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;

import java.util.Scanner;

public class InvalidOption extends MenuOption {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public String perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        return "Invalid option!";
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public boolean isSecureLoginRequired() {
        return false;
    }

}
