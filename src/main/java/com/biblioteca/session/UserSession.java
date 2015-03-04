package com.biblioteca.session;

import com.biblioteca.user.InvalidUserPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;

public class UserSession {

    public User currentUser;
    private UserList userList;

    private UserSession(User currentUser, UserList userList) {
        this.currentUser = currentUser;
        this.userList = userList;
    }

    public static UserSession createNew(UserList userList) throws InvalidUserPasswordCombination {
        return new UserSession(null, userList);
    }

    public void login() {

    }
}
