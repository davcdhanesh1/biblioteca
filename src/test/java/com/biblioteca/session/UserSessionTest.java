package com.biblioteca.session;

import com.biblioteca.io.Printer;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UserSessionTest {

    private UserList userList;
    private User dhanesh;
    private User frank;
    private Printer printer;
    private Scanner scanner;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private String input;

    @Before
    public void setUp() throws Exception {
        userList = new UserList();
        dhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        frank = User.customer("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);

        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        input = "777-4445\npassword\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
    }

    @Test
    public void testLogin() throws Exception, InvalidLibraryAndPasswordCombination {
        UserSession userSession = UserSession.createNew(userList, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "Enter your library Number: ",
                "Enter your password: "
        );

        userSession.login();

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(userSession.getCurrentUser(),is(dhanesh));
    }

    @Test(expected = InvalidLibraryAndPasswordCombination.class)
    public void testUnsuccessfulLogin() throws Exception, InvalidLibraryAndPasswordCombination {
        input = "777-4445\nInvalidPassword\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        UserSession userSession = UserSession.createNew(userList, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "Enter your library Number: ",
                "Enter your password: "
        );

        userSession.login();
        assertEquals(userSession.getCurrentUser(), null);
    }

    @Test
    public void testLoginWhenUserIsAlreadyLoggedInToCurrentSession() throws Exception, InvalidLibraryAndPasswordCombination {
        UserSession userSession = UserSession.createNew(userList, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "Enter your library Number: ",
                "Enter your password: "
        );
        userSession.login();

        // Calling login on userSession after user has already logged in
        userSession.login();
        assertThat(byteArrayOutputStream.toString(), is(expectedOutput));
    }
}