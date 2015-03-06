package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

import java.util.Scanner;

public class InvalidOption extends MenuOption {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        return new View("Invalid option!", printer, scanner);
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
