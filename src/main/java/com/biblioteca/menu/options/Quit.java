package com.biblioteca.menu.options;

import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import com.biblioteca.menu.options.MenuOption;
import com.biblioteca.session.UserSession;

import java.util.Scanner;

public class Quit extends MenuOption {

    public Quit() {
        super("Quit");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        printer.println("Book a week, keeps teacher away!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return false;
    }

    @Override
    public boolean isSecureLoginRequired() {
        return false;
    }
}
