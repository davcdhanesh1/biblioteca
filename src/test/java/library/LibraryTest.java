package library;

import io.Printer;
import book.Book;
import book.BookIsNotAvailable;
import book.BookList;
import book.BookNotFoundException;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LibraryTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");
    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    BookList bookList;
    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private Library library;

    private ByteArrayOutputStream outputStream;
    private Printer printer;

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        library = new Library(bookList,printer);
    }

    @Test
    public void testCheckOutBook() throws Exception, BookNotFoundException, BookIsNotAvailable {
        library.checkOut("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
        assertThat(outputStream.toString(),is("Thanks you! Enjoy the book\n"));
    }

    @Test
    public void testCheckOutBookWhenInvalidBookIdIsGiven() throws Exception, BookNotFoundException, BookIsNotAvailable {
        library.checkOut("10");

        assertThat(outputStream.toString(),is("Invalid Book to checkout\n"));
    }

    @Test
    public void testCheckOutBookWhenBookWithGivenIdIsNotAvailable() throws BookIsNotAvailable, BookNotFoundException {
        harryPotterAndThePhilosophersStone.checkOut();
        String expectedOutput = StringUtil.getOutputString("That book is not available");
        library.checkOut("1");
        assertThat(outputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testCheckInBook() throws Exception, BookIsNotAvailable {
        harryPotterAndThePhilosophersStone.checkOut();
        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("Thank you for returning the book.\n"));
    }

    @Test
    public void testCheckInBookWhenInvalidBookIdIsGiven() throws Exception, BookIsNotAvailable {
        library.returnBook("10");

        assertThat(outputStream.toString(),is("Invalid Book to return\n"));
    }

    @Test
    public void testCheckInBookWhenBookWithGivenIdIsNotCheckedOut() throws Exception, BookIsNotAvailable {
        harryPotterAndThePhilosophersStone.checkIn();

        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("That book is not checked out\n"));
    }
}