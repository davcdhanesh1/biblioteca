package com.biblioteca.model;

import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.view.View;

public class UserSession {

    private User currentUser;
    private UserList userList;

    private UserSession(User currentUser, UserList userList) {
        this.currentUser = currentUser;
        this.userList = userList;
    }

    public static UserSession createNew(UserList userList)  {
        return new UserSession(null, userList);
    }

    public void login(View view) throws InvalidLibraryAndPasswordCombination {
        if (currentUser != null) return;

        view.render("Enter your library Number: ");
        String libraryNumber = view.scan();
        view.render("Enter your password: ");
        String passWord = view.scan();
        currentUser = userList.findByLibraryNumberAndPassword(libraryNumber, passWord);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
