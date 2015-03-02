package com.biblioteca.menu;

import com.biblioteca.item.BookCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.BookIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.item.BookNotFoundException;

import java.util.Scanner;

public abstract class Menu {

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    public abstract void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned, InputValidationException;

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }
}
