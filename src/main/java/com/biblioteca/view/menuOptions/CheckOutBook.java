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
import com.biblioteca.view.View;

import java.util.Scanner;

public class CheckOutBook extends MenuOption {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        String option;
        printer.println(library.getAllBooks());
        printer.println("Enter id of Book: ");
        option = scanner.next();
        Validator.validate(option);
        return new View(library.checkOutBook(option, userSession), printer, scanner);
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

    @Override
    public boolean isSecureLoginRequired() {
        return true;
    }

}
