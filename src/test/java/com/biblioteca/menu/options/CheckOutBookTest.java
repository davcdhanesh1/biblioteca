package com.biblioteca.menu.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.library.Library;
import com.biblioteca.menu.options.CheckOutBook;
import com.biblioteca.session.UserSession;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CheckOutBookTest {

    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    BookList bookList;
    private final String JKRowling = "J K Rowling";
    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private CheckOutBook checkOutBook = new CheckOutBook();
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Library library;
    private Scanner scanner;
    private String input;
    private MovieList movieList;
    private UserSession mockUserSession;

    @Before
    public void setUp() throws Exception {
        mockUserSession = mock(UserSession.class);
        User mockCurrentUser = mock(User.class);
        when(mockUserSession.getCurrentUser()).thenReturn(mockCurrentUser);

        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        movieList = mock(MovieList.class);

        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(2, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        library = new Library(bookList, movieList, printer);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(checkOutBook.shouldContinueRunning(),is(true));
    }

    @Test
    public void testDescription() throws Exception {
        assertThat(checkOutBook.toString(), is("Checkout a Book"));
    }

    @Test
    public void testPerform() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        checkOutBook.perform(mockUserSession, library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987", "",
                "Enter id of Book: ",
                "Thanks you! Enjoy the book"

        );
        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));

        verify(mockUserSession.getCurrentUser(), times(1)).addItem(harryPotterAndThePhilosophersStone);
    }

    @Test
    public void testPerformWhenInputIsNotValid() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        Library mockLibrary = mock(Library.class);
        Printer mockPrinter = mock(Printer.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        try {
            checkOutBook.perform(mockUserSession, mockLibrary, mockPrinter, scanner);
            Assert.fail("Test did not fail for invalid input");
        } catch (InputValidationException e) {
            assertThat(e.getMessage(),is("Input has to be number"));
        }

        verify(mockUserSession.getCurrentUser(), never()).addItem(harryPotterAndThePhilosophersStone);
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(checkOutBook.isSecureLoginRequired(),is(true));
    }
}