package com.biblioteca.view.menuOptions;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.view.View;

import java.util.Scanner;

public class ReturnBook extends MenuOption {
    public ReturnBook() {
        super("Return a Book");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        printer.println("Enter id of Book: ");
        String option = scanner.next();
        Validator.validate(option);
        return new View(library.returnBook(option, userSession), printer, scanner);
    }

    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isSecureLoginRequired() {
        return true;
    }
}
