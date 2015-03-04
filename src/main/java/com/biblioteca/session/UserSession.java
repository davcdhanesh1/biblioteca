package com.biblioteca.session;

import com.biblioteca.io.Printer;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;

import java.util.Scanner;

public class UserSession {

    public User currentUser;
    private UserList userList;
    private Printer printer;
    private Scanner scanner;

    private UserSession(User currentUser, UserList userList, Printer printer, Scanner scanner) {
        this.currentUser = currentUser;
        this.userList = userList;
        this.printer = printer;
        this.scanner = scanner;
    }

    public static UserSession createNew(UserList userList, Printer printer, Scanner scanner)  {
        return new UserSession(null, userList, printer, scanner);
    }

    public void login() throws InvalidLibraryAndPasswordCombination {
        if (currentUser != null) return;

        scanner.useDelimiter("\n");
        printer.println("Enter your library Number: ");
        String libraryNumber = scanner.next();
        printer.println("Enter your password: ");
        printer.println("-----------------------------------------------------------------------------");
        String passWord = scanner.next();
        currentUser = userList.findByLibraryNumberAndPassword(libraryNumber, passWord);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
