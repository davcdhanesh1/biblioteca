package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.library.Library;

import java.util.Scanner;

public class ListAllMovies extends Menu {

    public ListAllMovies() {
        super("List Movies");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        library.printAllMovies();
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

}
