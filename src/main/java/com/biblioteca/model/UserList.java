package com.biblioteca.model;

import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;

import java.util.ArrayList;

public class UserList {
    ArrayList<User> users = new ArrayList<User>();

    public void add(User user) {
        users.add(user);
    }

    public User findByLibraryNumberAndPassword(String libraryNumber, String passWord) throws InvalidLibraryAndPasswordCombination {
        for(User user: users) {
            if (user.matches(libraryNumber, passWord)) return user;
        }
        throw new InvalidLibraryAndPasswordCombination("Invalid Library Number or Password !");
    }
}
