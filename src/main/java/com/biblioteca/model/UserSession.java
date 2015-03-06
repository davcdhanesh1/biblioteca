package com.biblioteca.model;

import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.io.Printer;
import com.biblioteca.view.View;

import java.util.Scanner;

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

    public void login(Printer printer, Scanner scanner) throws InvalidLibraryAndPasswordCombination {
        View view = new View(printer, scanner);
        if (currentUser != null) return;

        view.render("Enter your library Number: ");
        String libraryNumber = view.scan();
        view.render("Enter your password: ");
        String passWord = scanner.next();
        currentUser = userList.findByLibraryNumberAndPassword(libraryNumber, passWord);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
