package com.menu;

import com.io.Printer;
import com.library.Library;

import java.util.Scanner;

public class Quit extends Menu {

    public Quit() {
        super("Quit");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) {
        printer.println("Book a week, keeps teacher away!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return false;
    }
}
