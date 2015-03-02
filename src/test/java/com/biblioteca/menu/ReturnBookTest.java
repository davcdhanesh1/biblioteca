package com.biblioteca.menu;

import com.biblioteca.item.Book;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.BookList;
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

public class ReturnBookTest {

    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";

    private final String JKRowling = "J K Rowling";

    private Book harryPotterAndThePhilosophersStone;
    private Book harryPotterAndTheChambersOfSecrets;
    private ReturnBook returnBookOption = new ReturnBook();
    private Printer printer;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private Library library;
    private Scanner scanner;
    private String input;
    private BookList bookList;

    @Before
    public void setUp() throws Exception {
        input = "1\n";
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer  = new Printer(byteArrayOutputStream);
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        bookList = new BookList();
        harryPotterAndThePhilosophersStone = new Book(1, HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987);
        harryPotterAndTheChambersOfSecrets = new Book(1, HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987);
        bookList.add(harryPotterAndThePhilosophersStone);
        bookList.add(harryPotterAndTheChambersOfSecrets);

        library = new Library(bookList, printer);
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(returnBookOption.shouldContinueRunning(),is(true));
    }

    @Test
    public void testDescription() throws Exception {
        assertThat(returnBookOption.toString(),is("Return a Book"));
    }

    @Test
    public void testPerform() throws Exception, ItemNotFoundException, ItemCanNotBeReturned, InputValidationException {
        harryPotterAndThePhilosophersStone.checkOut();
        returnBookOption.perform(library, printer, scanner);
        String expectedOutput = StringUtil.getOutputString(
                "Enter id of Book: ",
                "Thank you for returning the book."
        );

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
        assertThat(harryPotterAndThePhilosophersStone.isCheckedOut(),is(false));
    }

    @Test
    public void testPerformWhenInputIsNotValid() throws InputValidationException, ItemNotFoundException, ItemCanNotBeReturned {
        Library mockLibrary = mock(Library.class);
        Printer mockPrinter = mock(Printer.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        try {
            returnBookOption.perform(mockLibrary, mockPrinter, scanner);
        } catch (InputValidationException e) {
            assertThat(e.getMessage(),is("Input has to be number"));
        }
    }

}