package com.biblioteca.session;

import com.biblioteca.user.InvalidUserPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;

public class UserSession {

    public final User currentUser;

    private UserSession(User currentUser) {
        this.currentUser = currentUser;
    }

    public static UserSession createNew(UserList userList, String libraryNumber, String passWord) throws InvalidUserPasswordCombination {
        User user = userList.findByLibraryNumberAndPassword(libraryNumber, passWord);
        return new UserSession(user);
    }
}
