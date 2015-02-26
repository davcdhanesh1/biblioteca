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

public class InvalidOptionTest {
    public final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");

    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer printer;
    private BookList bookList;
    private InvalidOption invalidOption;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        bookList = new BookList();
        bookList.add(new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE, JKRowling, 1987));
        bookList.add(new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS, JKRowling, 1987));
        invalidOption = new InvalidOption();
    }

    @Test
    public void testPerform() throws Exception {
        invalidOption.perform(bookList, printer);
        assertThat(byteArrayOutputStream.toString(), is("Invalid option!\n"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(invalidOption.toString(), is("Invalid Option!"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(invalidOption.shouldContinueRunning(),is(true));
    }
}
