package com.biblioteca.user;

import java.util.ArrayList;

public class UserList {
    ArrayList<User> users = new ArrayList<User>();

    public void add(User user) {
        users.add(user);
    }

    public User findByLibraryNumber(String libraryNumber) throws InvalidUserPasswordCombination {
        for(User user: users) {
            if (user.hasLibraryNumber(libraryNumber)) return user;
        }
        throw new InvalidUserPasswordCombination("Invalid Username or Password");
    }
}
