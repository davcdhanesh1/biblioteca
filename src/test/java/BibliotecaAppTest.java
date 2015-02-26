import IO.Printer;
import book.Book;
import book.BookList;
import menu.ListAllBook;
import menu.Menu;
import menu.MenuList;
import menu.Quit;
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
    public static final String harryPotterAndPhilosophersStone = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public static final String harryPotterAndChambersOfSecrets = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");
    private final String JKRowling = ResourceBundle.getBundle("bookList").getString("JKRowling");

    BookList bookList;
    private ByteArrayOutputStream outputStream;
    private Printer printer;
    private BibliotecaApp bibliotecaApp;
    private String input;
    private ByteArrayInputStream byteArrayInputStream;
    private Scanner scanner;
    private MenuList menuList;

    @Before
    public void setUp() throws Exception {
        input = "0\n";

        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);

        menuList = new MenuList();
        menuList.add(new ListAllBook());
        menuList.add(new Quit());

        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone, JKRowling, 1987));
        bookList.add(new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987));

        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
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

        assertThat(outputStream.toString(), containsString("-----------------------------------------------------------------------------\n"));
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
    public void testSelectListBookOption() throws Exception {
        input = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString(harryPotterAndChambersOfSecrets));
        assertThat(outputStream.toString(),containsString(harryPotterAndPhilosophersStone));
    }

    @Test
    public void testSelectingInvalidOption() throws Exception {
        input = "-1\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(bookList);

        assertThat(outputStream.toString(),containsString("Invalid option!"));
    }

    @Test
    public void testSelectingOptionsUntilQuitOptionIsSelected() throws Exception {
        input = "1\n-1\n2\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(bookList);

        String actualOutput = outputStream.toString();

        String expectedOutput = "Welcome To Biblioteca\n";
        expectedOutput += "-----------------------------------------------------------------------------\n";
        expectedOutput += "1. List Books\n";
        expectedOutput += "2. Quit\n";
        expectedOutput += "Select Option: \n";
        expectedOutput += "|Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987\n";
        expectedOutput += "|Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987\n";
        expectedOutput += "\n";
        expectedOutput += "Invalid option!\n";
        expectedOutput += "Book a week, keeps teacher away!\n";

        assertThat(actualOutput,is(expectedOutput));
    }
}