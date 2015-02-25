import IO.Printer;
import book.Book;
import book.BookList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    private String input;
    private ByteArrayInputStream byteArrayInputStream;
    private Scanner scanner;

    @Before
    public void setUp() throws Exception {
        input = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone, JKRowling, 1987));
        bookList.add(new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987));
        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner);
    }

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception {
        String expectedWelcomeToBibliotecaMsg = ResourceBundle.getBundle("bibliotecaAppMessagesTest").getString("WelcomeMessage");

        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString(expectedWelcomeToBibliotecaMsg));
    }

    @Test
    public void testSeparatorBetweenWelcomeMessageAndListOfBooks() throws Exception {
        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString(String.format("\n%-64s",'-')));
    }

    @Test
    public void testMenuOptionOnStart() throws Exception {
        String expectedMenuOptions = new String();
        expectedMenuOptions += "1. List Books";
        String selectOption = "Select Option: ";

        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString(expectedMenuOptions));
        assertThat(outputStream.toString(),containsString(selectOption));
    }

    @Test
    public void testUserShouldBeAbleToInputOption() throws Exception {
        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString(harryPotterAndChambersOfSecrets));
        assertThat(outputStream.toString(),containsString(harryPotterAndPhilosophersStone));
    }
}