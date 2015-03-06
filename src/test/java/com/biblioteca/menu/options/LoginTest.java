package com.biblioteca.menu.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.rental.InvalidItemException;
import com.biblioteca.rental.ItemCanNotBeReturned;
import com.biblioteca.rental.ItemIsNotAvailableForCheckOut;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class LoginTest {

    private Login loginOption;
    private Library mockLibrary;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Printer printer;
    private Scanner scanner;
    private User dhanesh;
    private UserSession userSession;

    @Before
    public void setUp() throws Exception {
        loginOption = new Login();
        mockLibrary = mock(Library.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayInputStream = new ByteArrayInputStream("777-4445\npassword\n".getBytes());
        printer = new Printer(byteArrayOutputStream);
        scanner = new Scanner(byteArrayInputStream);
        dhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        UserList userList = new UserList();
        userList.add(dhanesh);
        userSession = UserSession.createNew(userList);

    }

    @Test
    public void testPerform() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        loginOption.perform(userSession, mockLibrary, printer, scanner);
        assertThat(userSession.getCurrentUser(), is(dhanesh));
    }

    @Test
    public void testPerformWhenUserIsLoggedIntoCurrentSession() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        UserSession mockUserSession = mock(UserSession.class);
        when(mockUserSession.getCurrentUser()).thenReturn(mock(User.class));

        loginOption.perform(mockUserSession, mockLibrary, printer, scanner);

        verify(mockUserSession, never()).login(printer, scanner);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(loginOption.shouldContinueRunning(), is(true));
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(loginOption.isSecureLoginRequired(), is(false));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(loginOption.toString(), is("Login"));
    }
}