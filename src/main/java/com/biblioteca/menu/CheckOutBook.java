package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.item.ItemNotFoundException;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut, InputValidationException {
        String option;
        library.printAllBook();
        printer.println("Enter id of Book: ");
        option = scanner.next();
        Validator.validate(option);
        library.checkOutBook(option);
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
