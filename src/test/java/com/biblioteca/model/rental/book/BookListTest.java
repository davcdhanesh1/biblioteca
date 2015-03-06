package com.biblioteca.model.rental.book;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.*;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookListTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    BookList bookList;
    private final String JKRowling = "J K Rowling";
    private Book harryPotterAndThePhilosophersStoneBook;
    private Book harryPotterAndTheChambersOfSecretsBook;

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        harryPotterAndThePhilosophersStoneBook = new Book(10, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStoneBook);
        harryPotterAndTheChambersOfSecretsBook = new Book(20, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndTheChambersOfSecretsBook);
    }

    @Test
    public void testListOfBooks() throws Exception {
        assertThat(bookList.count(), is(2));
    }

    @Test
    public void testPrintListOfBooks() throws Exception {
        String expectedBookListOutput = StringUtil.getOutputString(
                "|10      |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|20      |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987"
        );

        assertThat(bookList.toString(),is(expectedBookListOutput));
    }

    @Test
    public void testPrintListOfUncheckedOutBook() throws Exception {
        String expectedBookListOutput = StringUtil.getOutputString(
                "|10      |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987"
        );

        harryPotterAndTheChambersOfSecretsBook.checkOut();

        assertThat(bookList.toString(),is(expectedBookListOutput));
    }

    @Test
    public void testFindFromAvailableBook() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut {
        assertThat(bookList.findFromAvailableById("10"),is(harryPotterAndThePhilosophersStoneBook));
    }

    @Test(expected = InvalidItemException.class)
    public void testFindFromAvailableBookWhenBookToBeFoundIsNotPresentInTheList() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut {
        bookList.findFromAvailableById("3");
    }

    @Test(expected = ItemIsNotAvailableForCheckOut.class)
    public void testFindingFromAvailableBookWhenBookToBeFoundIsAlreadyCheckedOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut {
        harryPotterAndTheChambersOfSecretsBook.checkOut();
        bookList.findFromAvailableById("20");
    }

    @Test
    public void testFindFromCheckedOutBooks() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        harryPotterAndThePhilosophersStoneBook.checkOut();
        assertThat(bookList.findFromCheckedOutById("10"),is(harryPotterAndThePhilosophersStoneBook));
    }

    @Test(expected = InvalidItemException.class)
    public void testFindFromCheckedOutBooksWhenBookToBeCheckedOutIsNotPresentInTheList() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        bookList.findFromCheckedOutById("3");
    }

    @Test(expected = ItemCanNotBeReturned.class)
    public void testFindFromCheckedOutBooksWhenBookToBeFoundIsNotPresentInTheListOfCheckedOutBooks() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        harryPotterAndTheChambersOfSecretsBook.checkIn();
        bookList.findFromCheckedOutById("20");
    }
}