package com.menu;

import com.io.Printer;
import com.library.Library;
import com.book.Book;
import com.book.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuitTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");

    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private BookList bookList;
    private Quit quit;
    private Library library;
    private ByteArrayInputStream byteArrayInputStream;
    private Scanner scanner;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        byte[] input = new String().getBytes();
        byteArrayInputStream = new ByteArrayInputStream(input);
        scanner =  new Scanner(byteArrayInputStream);

        bookList = new BookList();
        bookList.add(new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987));
        bookList.add(new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
        quit = new Quit();
        library = new Library(bookList,printer);
    }

    @Test
    public void testPerform() throws Exception {
        quit.perform(library, printer, scanner);
        assertThat(byteArrayOutputStream.toString(), is("Book a week, keeps teacher away!\n"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(quit.toString(),is("Quit"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(quit.shouldContinueRunning(),is(false));
    }
}
