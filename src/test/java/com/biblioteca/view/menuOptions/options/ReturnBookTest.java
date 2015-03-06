package com.biblioteca.view.menuOptions.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.BorrowedItemList;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.ReturnBook;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        User mockCurrentUser = mock(User.class);
        when(mockUserSession.getCurrentUser()).thenReturn(mockCurrentUser);

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

        BorrowedItemList borrowedItemList = new BorrowedItemList();
        library = new Library(bookList, movieList, borrowedItemList);
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
        String expectedOutput = StringUtil.getOutputString(
                "Enter id of Book: ",
                "Thank you for returning the book"
        );

        String output = returnBookOption.perform(mockUserSession, library, printer, scanner);
        new View(printer, scanner).render(output);

        assertEquals(byteArrayOutputStream.toString(), expectedOutput);
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
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
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(returnBookOption.isSecureLoginRequired(),is(true));
    }
}