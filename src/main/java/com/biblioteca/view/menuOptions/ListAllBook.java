package com.biblioteca.view.menuOptions;

import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;

public class ListAllBook extends MenuOption {

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public String perform(UserSession userSession, Library library, View view) {
        return library.getAllBooks();
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
