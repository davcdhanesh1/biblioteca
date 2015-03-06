package com.biblioteca.menu.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.rental.InvalidItemException;
import com.biblioteca.rental.ItemCanNotBeReturned;
import com.biblioteca.rental.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;

import java.util.Scanner;

public class CheckOutMovie extends MenuOption {

    public CheckOutMovie() { super("Checkout a Movie"); }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        String option;
        printer.println(library.getAllMovies());
        printer.println("Enter id of Movie: ");
        option = scanner.next();
        Validator.validate(option);
        printer.println(library.checkOutMovie(option, userSession));
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

    @Override
    public boolean isSecureLoginRequired() {
        return true;
    }
}
