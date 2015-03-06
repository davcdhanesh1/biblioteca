package com.biblioteca.menu.options;

import com.biblioteca.io.Printer;
import com.biblioteca.rental.book.Book;
import com.biblioteca.rental.book.BookList;
import com.biblioteca.rental.borrowedItem.BorrowedItemList;
import com.biblioteca.rental.movie.MovieList;
import com.biblioteca.library.Library;
import com.biblioteca.session.UserSession;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ListAllBookTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";

    private final String JKRowling = "J K Rowling";


    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private BookList bookList;
    private ListAllBook listAllBook;
    private Library library;
    private ByteArrayInputStream byteArrayInputStream;
    private Scanner scanner;
    private MovieList movieList;
    private UserSession userSession;

    @Before
    public void setUp() throws Exception {
        userSession = mock(UserSession.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        byte[] input = new String().getBytes();
        byteArrayInputStream = new ByteArrayInputStream(input);
        scanner =  new Scanner(byteArrayInputStream);

        bookList = new BookList();
        bookList.add(new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987));
        bookList.add(new Book(2, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
        listAllBook = new ListAllBook();

        movieList = mock(MovieList.class);

        BorrowedItemList borrowedItemList = new BorrowedItemList();
        library = new Library(bookList, movieList, borrowedItemList);
    }

    @Test
    public void testPerform() throws Exception {
        String expectedOutput = StringUtil.getOutputString(
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                ""
        );

        listAllBook.perform(userSession, library, printer, scanner);

        assertEquals(expectedOutput, byteArrayOutputStream.toString());
    }

    @Test
    public void testToString() throws Exception {
        assertThat(listAllBook.toString(), is("List Books"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(listAllBook.shouldContinueRunning(),is(true));
    }

    @Test
    public void testIsSecuredLoginRequired() throws Exception {
        assertThat(listAllBook.isSecureLoginRequired(),is(false));
    }
}
