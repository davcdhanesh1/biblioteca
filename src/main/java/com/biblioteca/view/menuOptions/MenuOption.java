package com.biblioteca.view.menuOptions;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;

import java.util.Scanner;

public abstract class MenuOption {

    private final String description;

    MenuOption(String description) {
        this.description = description;
    }

    public abstract String perform(UserSession userSession, Library library, Printer printer, Scanner scanner) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination;

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }

    public abstract boolean isSecureLoginRequired();
}
