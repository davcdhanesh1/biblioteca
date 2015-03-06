package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

import java.util.Scanner;

public class ListAllBook extends MenuOption {

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        return new View(library.getAllBooks(), printer, scanner);
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
