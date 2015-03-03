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
        dhanesh = new User("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        frank = new User("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);
    }

    @Test
    public void testFindByLibraryNumber() throws Exception, InvalidUserPasswordCombination {
        assertThat(userList.findByLibraryNumber("777-4446"),is(frank));
    }

    @Test(expected = InvalidUserPasswordCombination.class)
    public void testFindByLibraryNumberWhenUserWithGivenLibraryNumberIsDoesntExist() throws Exception, InvalidUserPasswordCombination {
        userList.findByLibraryNumber("777-4443");
    }
}