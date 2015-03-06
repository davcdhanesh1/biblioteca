package com.biblioteca.view.menuOptions;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.view.ViewRenderer;

import java.util.Scanner;

public class CheckOutMovie extends MenuOption {

    public CheckOutMovie() { super("Checkout a Movie"); }

    @Override
    public ViewRenderer perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        String option;
        printer.println(library.getAllMovies());
        printer.println("Enter id of Movie: ");
        option = scanner.next();
        Validator.validate(option);
        return new ViewRenderer(library.checkOutMovie(option, userSession), printer, scanner);
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

    @Override
    public boolean isSecureLoginRequired() {
        return true;
    }
}
