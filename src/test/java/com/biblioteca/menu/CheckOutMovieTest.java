package com.biblioteca.menu;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class CheckOutMovieTest {
    private CheckOutMovie checkOutMovieOption;
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Library library;
    private Scanner scanner;
    private String input;
    private MovieList movieList;
    private Movie whiplashMovie;
    private Movie birdmanMovie;
    private BookList bookList;

    @Before
    public void setUp() throws Exception {
        checkOutMovieOption = new CheckOutMovie();
        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bookList = mock(BookList.class);

        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        library = new Library(bookList, movieList, printer);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(checkOutMovieOption.shouldContinueRunning(), is(true));
    }

    @Test
    public void testDescription() throws Exception {
        assertThat(checkOutMovieOption.toString(),is("Checkout a Movie"));
    }

    @Test
    public void testPerform() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned {
        checkOutMovieOption.perform(library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                whiplashMovie.toString(),
                birdmanMovie.toString(),
                "",
                "Enter id of Movie: ",
                "Thanks you! Enjoy the movie"
        );

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(whiplashMovie.isCheckedOut(),is(true));
    }

    @Test
    public void testPerformWhenInputIsInvalid() throws Exception, ItemCanNotBeReturned, ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        Library mockLibrary = mock(Library.class);
        Printer mockPrinter = mock(Printer.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        try {
            checkOutMovieOption.perform(mockLibrary, mockPrinter, scanner);
            Assert.fail("Test did not fail for invalid input");
        } catch (InputValidationException e) {
            assertThat(e.getMessage(), is("Input has to be number"));
        }
    }
}