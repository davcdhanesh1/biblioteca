package com.biblioteca.menu.options;

import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;

import java.util.Scanner;

public class ListAllBook extends MenuOption {

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        library.printAllBook();
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
