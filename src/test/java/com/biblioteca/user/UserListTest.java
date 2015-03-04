package com.biblioteca.user;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserListTest {

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
    public void testFindByLibraryNumber() throws Exception, InvalidUserPasswordCombination {
        assertThat(userList.findByLibraryNumberAndPassword("777-4446", "password"),is(frank));
    }

    @Test(expected = InvalidUserPasswordCombination.class)
    public void testFindByLibraryNumberWhenUserWithGivenLibraryNumberIsDoesntExist() throws Exception, InvalidUserPasswordCombination {
        userList.findByLibraryNumberAndPassword("777-4443","pass");
    }
}