package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.session.UserSession;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

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
    private UserSession mockUserSession;

    @Before
    public void setUp() throws Exception {
        mockUserSession = mock(UserSession.class);
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
    public void testCheckOutBook() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        library.checkOutBook("1", mockUserSession);
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
        assertThat(outputStream.toString(),is("Thanks you! Enjoy the book\n"));
    }

    @Test
    public void testCheckOutBookWhenInvalidBookIdIsGiven() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        library.checkOutBook("10", mockUserSession);

        assertThat(outputStream.toString(),is("Invalid Book to checkout\n"));
    }

    @Test
    public void testCheckOutBookWhenBookWithGivenIdIsNotAvailable() throws ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();
        String expectedOutput = StringUtil.getOutputString("That book is not available");
        library.checkOutBook("1", mockUserSession);
        assertThat(outputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testCheckOutMovie() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        library.checkOutMovie("1", mockUserSession);

        assertThat(whiplashMovie.isCheckedOut(),is(true));
        assertThat(outputStream.toString(), is("Thanks you! Enjoy the movie\n"));
    }

    @Test
    public void testCheckOutMovieWhenInvalidMovieIdIsGiven() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        library.checkOutMovie("10", mockUserSession);

        assertThat(outputStream.toString(),is("Invalid Movie to checkout\n"));
    }

    @Test
    public void testCheckOutMovieWhenMovieWithGivenIdIsNotAvailableBecauseItIsAlreadyCheckedOut() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        whiplashMovie.checkOut();

        library.checkOutMovie("1", mockUserSession);

        assertThat(outputStream.toString(),is("That movie is not available\n"));
    }

    @Test
    public void testCheckInBook() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();
        library.returnBook("1", mockUserSession);
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("Thank you for returning the book.\n"));
    }

    @Test
    public void testCheckInBookWhenInvalidBookIdIsGiven() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        library.returnBook("10", mockUserSession);

        assertThat(outputStream.toString(),is("Invalid Book to return\n"));
    }

    @Test
    public void testCheckInBookWhenBookWithGivenIdIsNotCheckedOut() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkIn();

        library.returnBook("1", mockUserSession);
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("We already have this book !\n"));
    }
}