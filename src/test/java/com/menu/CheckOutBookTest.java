package com.menu;

import com.io.Printer;
import com.book.BookIsNotAvailable;
import com.library.Library;
import com.book.BookNotFoundException;
import testhelpers.StringUtil;
import com.book.Book;
import com.book.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CheckOutBookTest {

    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");
    BookList bookList;
    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");
    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private CheckOutBook checkOutBook = new CheckOutBook();
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Library library;
    private Scanner scanner;
    private String input;

    @Before
    public void setUp() throws Exception {
        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        library = new Library(bookList, printer);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(checkOutBook.shouldContinueRunning(),is(true));
    }

    @Test
    public void testDescription() throws Exception {
        assertThat(checkOutBook.toString(),is("Checkout a Book"));
    }

    @Test
    public void testPerform() throws Exception, BookNotFoundException, BookIsNotAvailable {
        checkOutBook.perform(library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Select a com.book: ",
                "Thanks you! Enjoy the com.book"

        );

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
    }
}