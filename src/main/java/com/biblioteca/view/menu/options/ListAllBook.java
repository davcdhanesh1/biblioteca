package com.biblioteca.view.menu.options;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;

import java.util.Scanner;

public class ListAllBook extends MenuOption {

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        printer.println(library.getAllBooks());
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
