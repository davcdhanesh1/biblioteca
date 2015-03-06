package com.biblioteca.view.menuOptions;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

public class ReturnBook extends MenuOption {
    public ReturnBook() {
        super("Return a Book");
    }

    @Override
    public String perform(UserSession userSession, Library library, View view) throws InvalidItemException, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        view.render("Enter id of Book: ");
        String option = view.scan();
        Validator.validate(option);
        return library.returnBook(option, userSession);
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
