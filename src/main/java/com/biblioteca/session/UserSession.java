package com.biblioteca.session;

import com.biblioteca.io.Printer;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;

import java.util.Scanner;

public class UserSession {

    private User currentUser;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;

    private UserSession(User currentUser, UserList userList) {
        this.currentUser = currentUser;
        this.userList = userList;
        this.printer = printer;
        this.scanner = scanner;
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
