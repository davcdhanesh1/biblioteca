package com.biblioteca.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;

import java.util.Scanner;

public class Quit extends Menu {

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
}
