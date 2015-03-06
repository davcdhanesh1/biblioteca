package com.biblioteca.view.menuOptions.options;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.BorrowedItemList;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.rental.Rating;
import com.biblioteca.model.Library;
import com.biblioteca.model.UserSession;
import com.biblioteca.view.View;
import com.biblioteca.view.menuOptions.ListAllMovies;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ListAllMoviesTest {

    private ListAllMovies listAllMovies;
    private Movie whiplashMovie;
    private Movie birdmanMovie;
    private MovieList movieList;
    private BookList bookList;
    private Library library;
    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private Scanner scanner;
    private UserSession userSession;

    @Before
    public void setUp() throws Exception {
        userSession = mock(UserSession.class);
        listAllMovies = new ListAllMovies();
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);
        scanner = new Scanner(System.in);

        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        bookList = mock(BookList.class);

        BorrowedItemList borrowedItemList = new BorrowedItemList();
        library = new Library(bookList, movieList, borrowedItemList);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(listAllMovies.toString(),is("List Movies"));
    }

    @Test
    public void testPerform() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned {
        String expectedMovieList = StringUtil.getOutputString(
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                ""
        );

        String output = listAllMovies.perform(userSession, library, printer, scanner);
        new View(printer, scanner).render(output);

        assertThat(byteArrayOutputStream.toString(), is(expectedMovieList));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(listAllMovies.shouldContinueRunning(),is(true));
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(listAllMovies.isSecureLoginRequired(),is(false));
    }
}