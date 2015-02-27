package com.biblioteca.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.book.BookIsNotAvailable;
import com.biblioteca.library.Library;
import com.biblioteca.book.BookNotFoundException;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailable {
        String option;
        library.printAllBook();
        printer.println("Select a com.biblioteca.book: ");
        option = scanner.next();
        library.checkOut(option);
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
