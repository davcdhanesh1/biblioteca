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

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone));
        bookList.add(new Book(harryPotterAndChambersOfSecrets));
        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run(printer,bookList);
    }

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception {
        String expectedWelcomeToBibliotecaMsg = ResourceBundle.getBundle("bibliotecaAppMessages").getString("WelcomeMessage");
        assertThat(outputStream.toString(),containsString(expectedWelcomeToBibliotecaMsg));
    }

    @Test
    public void testPrintingOfListOfBooksOnStartUp() throws Exception {
        String expectedListOfBooks = "\n" + harryPotterAndPhilosophersStone + "\n" + harryPotterAndChambersOfSecrets + "\n\n";
        assertThat(outputStream.toString(),containsString(expectedListOfBooks));

    }
}