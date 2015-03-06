package com.biblioteca.view.menuOptions;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

import java.util.Scanner;

public class CheckOutMovie extends MenuOption {

    public CheckOutMovie() { super("Checkout a Movie"); }

    @Override
    public String perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        View view = new View(printer, scanner);
        String option;
        view.render(library.getAllMovies());
        view.render("Enter id of Movie: ");
        option = view.scan();
        Validator.validate(option);
        return library.checkOutMovie(option, userSession);
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

    @Override
    public boolean isSecureLoginRequired() {
        return true;
    }
}
