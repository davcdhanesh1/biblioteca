package com.biblioteca.menu;

import com.biblioteca.item.book.Book;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CheckOutBookTest {

    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    BookList bookList;
    private final String JKRowling = "J K Rowling";
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
    public void etUp() throws Exception {
        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(2, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
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
    public void testPerform() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, InputValidationException {
        checkOutBook.perform(library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987", "",
                "Enter id of Book: ",
                "Thanks you! Enjoy the book"

        );

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(true));
    }

    @Test
    public void testPerformWhenInputIsNotValid() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        Library mockLibrary = mock(Library.class);
        Printer mockPrinter = mock(Printer.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        try {
            checkOutBook.perform(mockLibrary, mockPrinter, scanner);
        } catch (InputValidationException e) {
            assertThat(e.getMessage(),is("Input has to be number"));
        }
    }
}