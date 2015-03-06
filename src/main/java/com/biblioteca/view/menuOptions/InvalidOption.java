package com.biblioteca.view.menuOptions;

import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

public class InvalidOption extends MenuOption {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public String perform(UserSession userSession, Library library, View view) {
        return "Invalid option!";
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
