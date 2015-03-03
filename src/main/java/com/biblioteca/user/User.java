package com.biblioteca.user;

import com.biblioteca.item.Item;

import java.util.ArrayList;

public class User {
    private final String libraryNumber;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final ArrayList<Item> items;

    public User(String libraryNumber, String name, String email, String phoneNumber, ArrayList<Item> items) {

        this.libraryNumber = libraryNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.items = items;
    }

    @Override
    public String toString() {
        return String.format("|%-8s|%-32s|%-32s|%-10s", libraryNumber, name, email, phoneNumber);
    }
}
