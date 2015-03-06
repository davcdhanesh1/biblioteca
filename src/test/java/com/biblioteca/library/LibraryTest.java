package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.borrowedItem.BorrowedItem;
import com.biblioteca.item.borrowedItem.BorrowedItemList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.session.UserSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Library.class)
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
    private BorrowedItemList mockBorrowedItemList;

    @Before
    public void setUp() throws Exception {
        mockUserSession = mock(UserSession.class);
        mockBorrowedItemList = mock(BorrowedItemList.class);
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
        library = new Library(bookList, movieList, mockBorrowedItemList, printer);
    }

    @Test
    public void testCheckOutBook() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMessageFromLibrary = library.checkOutBook("1", mockUserSession);

        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(), is(true));
        assertEquals(outPutMessageFromLibrary, "Thanks you! Enjoy the book");
        verify(mockBorrowedItemList, times(1)).add(mockBorrowedItem);
    }

    @Test
    public void testCheckOutBookWhenInvalidBookIdIsGiven() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMessageFromLibrary = library.checkOutBook("10", mockUserSession);

        assertEquals(outPutMessageFromLibrary, "Invalid Book to checkout");
        verify(mockBorrowedItemList, never()).add(mockBorrowedItem);
    }

    @Test
    public void testCheckOutBookWhenBookWithGivenIdIsNotAvailable() throws Exception {
        harryPotterAndThePhilosophersStone.checkOut();
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMessageFromLibrary = library.checkOutBook("1", mockUserSession);

        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(), is(true));
        assertEquals(outPutMessageFromLibrary, "This book is not available");
        verify(mockBorrowedItemList, never()).add(mockBorrowedItem);
    }

    @Test
    public void testCheckOutMovie() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outputMessageFromLibrary = library.checkOutMovie("1", mockUserSession);

        assertThat(whiplashMovie.isCheckedOut(),is(true));
        assertEquals(outputMessageFromLibrary, ("Thanks you! Enjoy the movie"));
        verify(mockBorrowedItemList, times(1)).add(mockBorrowedItem);
    }

    @Test
    public void testCheckOutMovieWhenInvalidMovieIdIsGiven() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMessageFromLibrary = library.checkOutMovie("10", mockUserSession);

        assertEquals(outPutMessageFromLibrary, "Invalid Movie to checkout");
        verify(mockBorrowedItemList, never()).add(mockBorrowedItem);
    }

    @Test
    public void testCheckOutMovieWhenMovieWithGivenIdIsNotAvailableBecauseItIsAlreadyCheckedOut() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        whiplashMovie.checkOut();
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMessageFromLibrary = library.checkOutMovie("1", mockUserSession);

        assertThat(whiplashMovie.isCheckedOut(), is(true));
        assertEquals(outPutMessageFromLibrary, "This movie is not available");
        verify(mockBorrowedItemList, never()).add(mockBorrowedItem);
    }

    @Test
    public void testCheckInBook() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMsgFromLibrary = library.returnBook("1", mockUserSession);

        assertEquals(outPutMsgFromLibrary, "Thank you for returning the book");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(), is(false));
        verify(mockBorrowedItemList, times(1)).remove(mockBorrowedItem);
    }

    @Test
    public void testCheckInBookWhenInvalidBookIdIsGiven() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMsgFromLibrary = library.returnBook("10", mockUserSession);

        assertEquals(outPutMsgFromLibrary, "Invalid Book to return");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        verify(mockBorrowedItemList, never()).remove(mockBorrowedItem);
    }

    @Test
    public void testCheckInBookWhenBookWithGivenIdIsNotCheckedOut() throws Exception, InvalidItemException, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkIn();

        BorrowedItem mockBorrowedItem = mock(BorrowedItem.class);
        whenNew(BorrowedItem.class).withAnyArguments().thenReturn(mockBorrowedItem);

        String outPutMsgFromLibrary = library.returnBook("1", mockUserSession);

        assertEquals(outPutMsgFromLibrary, "We already have this book !");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        verify(mockBorrowedItemList, never()).remove(mockBorrowedItem);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        String outputMsgFromLibrary = library.getAllBooks();

        String expectedBookListOutput = StringUtil.getOutputString(
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987"
        );

        assertEquals(expectedBookListOutput, outputMsgFromLibrary);
    }

    @Test
    public void testGetAllMovies() throws Exception {
        String outputMsgFromLibrary = library.getAllMovies();

        String expectedMovieList = StringUtil.getOutputString(
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN"
        );

        assertEquals(expectedMovieList, outputMsgFromLibrary);
    }
}