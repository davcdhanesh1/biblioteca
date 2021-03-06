package com.biblioteca.view.menuOptions.options;

import com.biblioteca.io.Printer;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.RentedItemList;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.InvalidOption;
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
    private View view;

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

        RentedItemList rentedItemList = new RentedItemList();
        view = new View(printer, scanner);
        library = new Library(bookList, movieList, rentedItemList);
    }

    @Test
    public void testPerform() throws Exception {
        String output = invalidOption.perform(userSession, library, view);
        new View(printer, scanner).render(output);
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
