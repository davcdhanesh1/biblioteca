package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.library.Library;

import java.util.Scanner;

public class CheckOutMovie extends Menu{

    public CheckOutMovie() { super("Checkout a Movie"); }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        String option;
        library.printAllMovies();
        printer.println("Enter id of Movie: ");
        option = scanner.next();
        Validator.validate(option);
        library.checkOutMovie(option);
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

}
