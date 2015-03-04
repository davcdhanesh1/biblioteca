package com.biblioteca.session;

import com.biblioteca.user.InvalidUserPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void testCreateNew() throws Exception, InvalidUserPasswordCombination {
        String passWord = "password";
        String libraryNumber = "777-4445";
        UserSession userSession = UserSession.createNew(userList, libraryNumber, passWord);

        assertThat(userSession.currentUser,is(dhanesh));
    }

    @Test(expected = InvalidUserPasswordCombination.class)
    public void testCreateNewSessionWhenInvalidUserNamePassWordCombinationIsGiven() throws Exception, InvalidUserPasswordCombination {
        String passWord = "password";
        String libraryNumber = "777-4442";
        UserSession userSession = UserSession.createNew(userList, libraryNumber, passWord);
        System.out.println(userSession.currentUser);
    }
}