package com.biblioteca.menu;

import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.session.UserSession;

import java.util.Scanner;

public abstract class Menu {

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    public abstract void perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException;

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }
}
