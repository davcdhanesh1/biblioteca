package com.biblioteca.model.menu.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;

import java.util.Scanner;

public class ListAllMovies extends MenuOption {

    public ListAllMovies() {
        super("List Movies");
    }

    @Override
    public void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        printer.println(library.getAllMovies());
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
