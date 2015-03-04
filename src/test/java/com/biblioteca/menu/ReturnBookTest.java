package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ReturnBookTest {

    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";

    private final String JKRowling = "J K Rowling";

    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private ReturnBook returnBookOption = new ReturnBook();
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Library library;
    private Scanner scanner;
    private String input;
    private BookList bookList;
    private MovieList movieList;
    private UserSession mockUserSession;

    @Before
    public void setUp() throws Exception {
        mockUserSession = mock(UserSession.class);
        mockUserSession.currentUser = mock(User.class);

        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(1, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        movieList = mock(MovieList.class);
        library = new Library(bookList, movieList, printer);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(returnBookOption.shouldContinueRunning(),is(true));
    }

    @Test
    public void testDescription() throws Exception {
        assertThat(returnBookOption.toString(),is("Return a Book"));
    }

    @Test
    public void testPerform() throws Exception, InvalidItemException, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        harryPotterAndThePhilosophersStone.checkOut();
        returnBookOption.perform(mockUserSession, library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "Enter id of Book: ",
                "Thank you for returning the book."
        );

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));

        verify(mockUserSession.currentUser, times(1)).removeItem(harryPotterAndThePhilosophersStone);
        verify(mockUserSession, times(1)).login();
    }

    @Test
    public void testPerformWhenInputIsNotValid() throws InputValidationException, InvalidItemException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        Library mockLibrary = mock(Library.class);
        Printer mockPrinter = mock(Printer.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        try {
            returnBookOption.perform(mockUserSession, mockLibrary, mockPrinter, scanner);
        } catch (InputValidationException e) {
            assertThat(e.getMessage(),is("Input has to be number"));
        }

        verify(mockUserSession.currentUser, never()).removeItem(harryPotterAndThePhilosophersStone);
        verify(mockUserSession, times(1)).login();
    }

    @Test
    public void testPerformWhenLoginIsNotUnSuccessful() throws Exception, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();

        Library mockLibrary = mock(Library.class);
        doThrow(new InvalidLibraryAndPasswordCombination("Invalid Library Number and Password combination pair"))
                .when(mockUserSession).login();
        returnBookOption.perform(mockUserSession, mockLibrary, printer, scanner);

        assertThat(byteArrayOutputStream.toString(),is("Invalid Library Number and Password combination pair\n"));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));

        verify(mockUserSession.currentUser, never()).removeItem(harryPotterAndThePhilosophersStone);
        verify(mockLibrary, never()).returnBook("1", mockUserSession);
    }
}