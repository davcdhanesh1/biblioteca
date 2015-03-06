package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;

import java.util.Scanner;

public class ListAllBook extends MenuOption {

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public String perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        return library.getAllBooks();
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
