package com.biblioteca.view.menuOptions.options;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.User;
import com.biblioteca.model.UserList;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.Login;
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
    private View view;

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
        view = new View(printer, scanner);
        userSession = UserSession.createNew(userList);

    }

    @Test
    public void testPerform() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        loginOption.perform(userSession, mockLibrary, view);
        assertThat(userSession.getCurrentUser(), is(dhanesh));
    }

    @Test
    public void testPerformWhenUserIsLoggedIntoCurrentSession() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        UserSession mockUserSession = mock(UserSession.class);
        when(mockUserSession.getCurrentUser()).thenReturn(mock(User.class));

        String output = loginOption.perform(mockUserSession, mockLibrary, view);
        new View(printer, scanner).render(output);

        assertThat(byteArrayOutputStream.toString(), is("Successfully logged in.\n"));
        verify(mockUserSession, never()).login(view);
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