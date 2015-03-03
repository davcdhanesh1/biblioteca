package com.biblioteca.user;

import java.util.ArrayList;

public class UserList {
    ArrayList<User> users = new ArrayList<User>();

    public void add(User user) {
        users.add(user);
    }

    public User findByLibraryNumberAndPassword(String libraryNumber, String passWord) throws InvalidUserPasswordCombination {
        for(User user: users) {
            if (user.matches(libraryNumber, passWord)) return user;
        }
        throw new InvalidUserPasswordCombination("Invalid Username or Password");
    }
}
