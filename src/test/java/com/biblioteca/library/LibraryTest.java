package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LibraryTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    private final String JKRowling = "J K Rowling";

    BookList bookList;
    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private Library library;

    private ByteArrayOutputStream outputStream;
    private Printer printer;
    private MovieList movieList;
    private Movie whiplashMovie;
    private Movie birdmanMovie;

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(2, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        library = new Library(bookList, movieList, printer);
    }

    @Test
    public void testCheckOutBook() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        library.checkOutBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
        assertThat(outputStream.toString(),is("Thanks you! Enjoy the book\n"));
    }

    @Test
    public void testCheckOutBookWhenInvalidBookIdIsGiven() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        library.checkOutBook("10");

        assertThat(outputStream.toString(),is("Invalid Book to checkout\n"));
    }

    @Test
    public void testCheckOutBookWhenBookWithGivenIdIsNotAvailable() throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        harryPotterAndThePhilosophersStone.checkOut();
        String expectedOutput = StringUtil.getOutputString("That book is not available");
        library.checkOutBook("1");
        assertThat(outputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testCheckOutMovie() throws Exception, ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        library.checkOutMovie("1");

        assertThat(whiplashMovie.isCheckedOut(),is(true));
        assertThat(outputStream.toString(), is("Thanks you! Enjoy the Movie\n"));
    }

    @Test
    public void testCheckOutMovieWhenInvalidMovieIdIsGiven() throws Exception, ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        library.checkOutMovie("10");

        assertThat(outputStream.toString(),is("Invalid Movie to checkout\n"));
    }

    @Test
    public void testCheckOutMovieWhenMovieWithGivenIdIsNotAvailableBecauseItIsAlreadyCheckedOut() throws Exception, ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        whiplashMovie.checkOut();

        library.checkOutMovie("1");

        assertThat(outputStream.toString(),is("That movie is not available out"));
    }

    @Test
    public void testCheckInBook() throws Exception, ItemNotFoundException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();
        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("Thank you for returning the book.\n"));
    }

    @Test
    public void testCheckInBookWhenInvalidBookIdIsGiven() throws Exception, ItemNotFoundException, ItemCanNotBeReturned {
        library.returnBook("10");

        assertThat(outputStream.toString(),is("Invalid Book to return\n"));
    }

    @Test
    public void testCheckInBookWhenBookWithGivenIdIsNotCheckedOut() throws Exception, ItemNotFoundException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkIn();

        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("We already have this book !\n"));
    }
}