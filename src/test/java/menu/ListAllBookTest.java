package menu;

import IO.Printer;
import book.Book;
import book.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListAllBookTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");

    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private BookList bookList;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        bookList = new BookList();
        bookList.add(new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987));
        bookList.add(new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
    }

    @Test
    public void testPerform() throws Exception {
        String expectedListOfAllBooks = new String();
        expectedListOfAllBooks += "|Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987\n";
        expectedListOfAllBooks += "|Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987\n";
        expectedListOfAllBooks += "\n";

        Menu.ListAllBook.perform(bookList,printer);

        assertThat(byteArrayOutputStream.toString(),is(expectedListOfAllBooks));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(Menu.ListAllBook.toString(), is("1. List Books"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(Menu.Quit.shouldContinueRunning(),is(false));
    }
}
