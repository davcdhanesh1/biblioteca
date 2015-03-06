package com.biblioteca.view.menuOptions;

import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

public class Quit extends MenuOption {

    public Quit() {
        super("Quit");
    }

    @Override
    public String perform(UserSession userSession, Library library, View view) {
        return "Book a week, keeps teacher away!";
    }

    @Override
    public boolean shouldContinueRunning() {
        return false;
    }

    @Override
    public boolean isSecureLoginRequired() {
        return false;
    }
}
