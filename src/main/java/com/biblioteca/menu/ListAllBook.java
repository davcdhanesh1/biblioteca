package com.biblioteca.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;

import java.util.Scanner;

public class ListAllBook extends Menu{

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) {
        library.printAllBook();
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }
}
