package com.biblioteca.book;

import testhelpers.StringUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookListTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    BookList bookList;
    private final String JKRowling = "J K Rowling";
    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        harryPotterAndTheChambersOfSecrets = new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndTheChambersOfSecrets);
    }

    @Test
    public void testListOfBooks() throws Exception {
        assertThat(bookList.count(), is(2));
    }

    @Test
    public void testPrintListOfBooks() throws Exception {
        String expectedBookListOutput = StringUtil.getOutputString(
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987"
        );

        assertThat(bookList.toString(),is(expectedBookListOutput));
    }

    @Test
    public void testPrintListOfUncheckedOutBook() throws Exception {
        String expectedBookListOutput = StringUtil.getOutputString(
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987"
        );

        harryPotterAndTheChambersOfSecrets.checkOut();

        assertThat(bookList.toString(),is(expectedBookListOutput));
    }

    @Test
    public void testFindFromAvailableBook() throws Exception, BookNotFoundException, BookIsNotAvailable {
        assertThat(bookList.findFromAvailableBook("1"),is(harryPotterAndThePhilosophersStone));
    }

    @Test(expected = BookNotFoundException.class)
    public void testFindFromAvailableBookWhenBookToBeFoundIsNotPresentInTheList() throws Exception, BookNotFoundException, BookIsNotAvailable {
        bookList.findFromAvailableBook("3");
    }

    @Test(expected = BookIsNotAvailable.class)
    public void testFindingAlreadyCheckedOutBook() throws Exception, BookNotFoundException, BookIsNotAvailable {
        harryPotterAndTheChambersOfSecrets.checkOut();
        bookList.findFromAvailableBook("2");
    }

    @Test
    public void testFindFromCheckedOutBooks() throws Exception, BookNotFoundException, BookIsNotAvailable {
        harryPotterAndThePhilosophersStone.checkOut();
        assertThat(bookList.findFromCheckedOutBooksById("1"),is(harryPotterAndThePhilosophersStone));
    }

    @Test(expected = BookNotFoundException.class)
    public void testFindFromCheckedOutBooksWhenBookToBeCheckedOutIsNotPresentInTheList() throws Exception, BookNotFoundException, BookIsNotAvailable {
        bookList.findFromCheckedOutBooksById("3");
    }

    @Test(expected = BookIsNotAvailable.class)
    public void testFindFromCheckedOutBooksWhenBookToBeFoundIsNotPresentInTheListOfCheckedOutBooks() throws Exception, BookNotFoundException, BookIsNotAvailable {
        harryPotterAndTheChambersOfSecrets.checkIn();
        bookList.findFromCheckedOutBooksById("2");
    }
}