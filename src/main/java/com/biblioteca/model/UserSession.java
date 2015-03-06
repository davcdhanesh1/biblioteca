package com.biblioteca.model;

import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;

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
        if (currentUser != null) return;

        printer.println("Enter your library Number: ");
        String libraryNumber = scanner.next();
        printer.println("Enter your password: ");
        String passWord = scanner.next();
        currentUser = userList.findByLibraryNumberAndPassword(libraryNumber, passWord);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
