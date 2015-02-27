package com.menu;

import com.io.Printer;
import com.book.BookIsNotAvailable;
import com.library.Library;
import com.book.BookNotFoundException;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailable {
        String option;
        library.printAllBook();
        printer.println("Select a com.book: ");
        option = scanner.next();
        library.checkOut(option);
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
