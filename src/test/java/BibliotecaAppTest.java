import IO.OutPutPrinter;
import book.Book;
import book.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {
    public static final String harryPotterAndPhilosophersStone
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public static final String harryPotterAndChambersOfSecrets
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");
    BookList bookList;
    private ByteArrayOutputStream outputStream;
    private OutPutPrinter outPutPrinter;
    private BibliotecaApp bibliotecaApp;
    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone, JKRowling, 1987));
        bookList.add(new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987));
        outputStream = new ByteArrayOutputStream();
        outPutPrinter = new OutPutPrinter(outputStream);
        bibliotecaApp = new BibliotecaApp(outPutPrinter);
        bibliotecaApp.run(bookList);
    }

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception {
        String expectedWelcomeToBibliotecaMsg = ResourceBundle.getBundle("bibliotecaAppMessagesTest").getString("WelcomeMessage");
        assertThat(outputStream.toString(),containsString(expectedWelcomeToBibliotecaMsg));
    }

    @Test
    public void testSeparatorBetweenWelcomeMessageAndListOfBooks() throws Exception {
        assertThat(outputStream.toString(),containsString(String.format("\n%-64s",'-')));
    }

    @Test
    public void testMenuOptionOnStart() throws Exception {
        String expectedMenuOptions = new String();
        expectedMenuOptions += "1. List Books";
        String selectOption = "Select Option: ";

        assertThat(outputStream.toString(),containsString(expectedMenuOptions));
        assertThat(outputStream.toString(),containsString(selectOption));
    }
}