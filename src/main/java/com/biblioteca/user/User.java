package com.biblioteca.user;

import com.biblioteca.rental.Item;

import java.util.ArrayList;

public class User {
    private final String libraryNumber;
    private final String name;
    private String passWord;
    private final String email;
    private final String phoneNumber;
    private boolean isAdmin;
    private ArrayList<Item> borrowedItems;
    private boolean admin;

    private User(String libraryNumber, String name, String passWord, String email, String phoneNumber, boolean isAdmin) {

        this.libraryNumber = libraryNumber;
        this.name = name;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.borrowedItems = new ArrayList<Item>();
    }

    @Override
    public String toString() {
        return String.format("|%-8s|%-32s|%-32s|%-10s", libraryNumber, name, email, phoneNumber);
    }

    public boolean matches(String libraryNumber, String passWord) {
        return this.libraryNumber.equals(libraryNumber) && this.passWord.equals(passWord);
    }

    public static User admin(String libraryNumber, String name, String password, String email, String phoneNumber) {
        return new User(libraryNumber, name, password, email, phoneNumber, true);
    }

    public static User customer(String libraryNumber, String name, String password, String email, String phoneNumber) {
        return new User(libraryNumber, name, password, email, phoneNumber, false);
    }

    public boolean isAdmin() {
        return isAdmin == true;
    }

    public String description() {
        return String.format("|%-8s|%-16s", libraryNumber, name);
    }
}
