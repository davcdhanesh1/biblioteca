package com.biblioteca.view.menuOptions;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

import java.util.Scanner;

public class ListAllMovies extends MenuOption {

    public ListAllMovies() {
        super("List Movies");
    }

    @Override
    public View perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        return new View(library.getAllMovies(), printer, scanner);
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public boolean isSecureLoginRequired() {
        return false;
    }

}
