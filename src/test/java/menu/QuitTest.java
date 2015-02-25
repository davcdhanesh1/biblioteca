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
        Menu.Quit.perform(bookList, printer);
        assertThat(byteArrayOutputStream.toString(), is("Book a week, keeps teacher away!\n"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(Menu.Quit.toString(),is("2. Quit"));
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(Menu.Quit.shouldContinueRunning(),is(false));
    }
}
