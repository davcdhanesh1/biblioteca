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
    private ListAllBook listAllBook;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        bookList = new BookList();
        bookList.add(new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987));
        bookList.add(new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
        listAllBook = new ListAllBook();
    }

    @Test
    public void testPerform() throws Exception {
        String expectedListOfAllBooks = new String();
        expectedListOfAllBooks += "|Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987\n";
        expectedListOfAllBooks += "|Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987\n";
        expectedListOfAllBooks += "\n";

        listAllBook.perform(bookList, printer);

        assertThat(byteArrayOutputStream.toString(),is(expectedListOfAllBooks));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(listAllBook.toString(), is("List Books"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(listAllBook.shouldContinueRunning(),is(true));
    }
}
