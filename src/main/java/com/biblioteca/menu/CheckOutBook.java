package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.book.BookIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.book.BookNotFoundException;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailableForCheckOut, InputValidationException {
        String option;
        library.printAllBook();
        printer.println("Enter id of Book: ");
        option = scanner.next();
        Validator.validate(option);
        library.checkOut(option);
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
