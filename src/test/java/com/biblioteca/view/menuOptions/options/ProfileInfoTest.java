package com.biblioteca.view.menuOptions.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.ProfileInfo;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfileInfoTest {

    private ProfileInfo profileInfo;
    private UserSession mockUserSession;
    private User currentUser;
    private Library mockLibrary;
    private Scanner mockScanner;
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void setUp() throws Exception {
        mockLibrary = mock(Library.class);
        mockScanner = new Scanner(System.in);
        currentUser = User.customer("777-4445", "dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        profileInfo = new ProfileInfo();
        mockUserSession = mock(UserSession.class);
        when(mockUserSession.getCurrentUser()).thenReturn(currentUser);

        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);
    }

    @Test
    public void testPerform() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        String expectedString = "|777-4445|dhanesh                         |davcdhanesh1@gmail.com          |9096904102\n";

        View view = profileInfo.perform(mockUserSession, mockLibrary, printer, mockScanner);
        view.render();

        assertThat(byteArrayOutputStream.toString(), is(expectedString));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(profileInfo.shouldContinueRunning(),is(true));
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(profileInfo.isSecureLoginRequired(),is(true));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(profileInfo.toString(), is("Profile information"));
    }
}