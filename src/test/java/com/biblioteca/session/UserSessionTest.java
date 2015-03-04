package com.biblioteca.session;

import com.biblioteca.user.User;
import com.biblioteca.user.UserList;
import org.junit.Before;

public class UserSessionTest {

    private UserList userList;
    private User dhanesh;
    private User frank;

    @Before
    public void setUp() throws Exception {
        userList = new UserList();
        dhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        frank = User.customer("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);
    }

}