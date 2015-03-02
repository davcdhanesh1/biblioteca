package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.*;
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

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(2, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        library = new Library(bookList,printer);
    }

    @Test
    public void testCheckOutBook() throws Exception, BookNotFoundException, BookIsNotAvailableForCheckOut {
        library.checkOut("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
        assertThat(outputStream.toString(),is("Thanks you! Enjoy the book\n"));
    }

    @Test
    public void testCheckOutBookWhenInvalidBookIdIsGiven() throws Exception, BookNotFoundException, BookIsNotAvailableForCheckOut {
        library.checkOut("10");

        assertThat(outputStream.toString(),is("Invalid Book to checkout\n"));
    }

    @Test
    public void testCheckOutBookWhenBookWithGivenIdIsNotAvailable() throws BookIsNotAvailableForCheckOut, BookNotFoundException {
        harryPotterAndThePhilosophersStone.checkOut();
        String expectedOutput = StringUtil.getOutputString("That book is not available");
        library.checkOut("1");
        assertThat(outputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testCheckInBook() throws Exception,  BookNotFoundException, BookCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkOut();
        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("Thank you for returning the book.\n"));
    }

    @Test
    public void testCheckInBookWhenInvalidBookIdIsGiven() throws Exception, BookNotFoundException, BookCanNotBeReturned {
        library.returnBook("10");

        assertThat(outputStream.toString(),is("Invalid Book to return\n"));
    }

    @Test
    public void testCheckInBookWhenBookWithGivenIdIsNotCheckedOut() throws Exception, BookNotFoundException, BookCanNotBeReturned {
        harryPotterAndThePhilosophersStone.checkIn();

        library.returnBook("1");
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
        assertThat(outputStream.toString(),is("We already have this book !\n"));
    }
}