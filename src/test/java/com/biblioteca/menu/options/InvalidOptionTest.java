package com.biblioteca.menu.options;

import com.biblioteca.io.Printer;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.library.Library;
import com.biblioteca.menu.options.InvalidOption;
import com.biblioteca.session.UserSession;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class InvalidOptionTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";

    private final String JKRowling = "J K Rowling";

    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private BookList bookList;
    private Library library;
    private InvalidOption invalidOption;
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
        bookList.add(new Book(1, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
        invalidOption = new InvalidOption();

        movieList = mock(MovieList.class);

        library = new Library(bookList, movieList, printer);
    }

    @Test
    public void testPerform() throws Exception {
        invalidOption.perform(userSession, library, printer, scanner);
        assertThat(byteArrayOutputStream.toString(), is("Invalid option!\n"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(invalidOption.toString(), is("Invalid Option!"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(invalidOption.shouldContinueRunning(),is(true));
    }

    @Test
    public void testIsSecuredLoginRequired() throws Exception {
        assertThat(invalidOption.isSecureLoginRequired(),is(false));
    }
}
