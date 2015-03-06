package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.ViewRenderer;

import java.util.Scanner;

public class Quit extends MenuOption {

    public Quit() {
        super("Quit");
    }

    @Override
    public ViewRenderer perform(UserSession userSession, Library library, Printer printer, Scanner scanner) {
        return new ViewRenderer("Book a week, keeps teacher away!", printer, scanner);
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