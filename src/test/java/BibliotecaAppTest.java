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
    private Printer printer;
    private BibliotecaApp bibliotecaApp;
    private final String JKRowling
            = ResourceBundle.getBundle("bookList").getString("JKRowling");

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone, JKRowling, 1987));
        bookList.add(new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987));
        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run(printer,bookList);
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
    public void testPrintingOfListOfBooksOnStartUp() throws Exception {
        String expectedBookListOutput = new String();
        expectedBookListOutput += "|Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987\n";
        expectedBookListOutput += "|Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987\n";

        assertThat(outputStream.toString(),containsString(expectedBookListOutput));

    }
}