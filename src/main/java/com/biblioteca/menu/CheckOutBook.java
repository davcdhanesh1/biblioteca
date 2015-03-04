package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        try {
            userSession.login();
        } catch (InvalidLibraryAndPasswordCombination e) {
            printer.println(e.getMessage());
            return;
        }
        String option;
        library.printAllBook();
        printer.println("Enter id of Book: ");
        option = scanner.next();
        Validator.validate(option);
        library.checkOutBook(option, userSession);
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

}
