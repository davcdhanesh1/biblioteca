import IO.Printer;
import library.Library;
import book.Book;
import book.BookList;
import book.BookNotFoundException;
import menu.CheckOutBook;
import menu.ListAllBook;
import menu.MenuList;
import menu.Quit;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

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
    private String inputForSelectingBook;
    private ByteArrayInputStream byteArrayInputStream;
    private Scanner scanner;
    private MenuList menuList;
    private Library library;
    private String inputForSelectingMenu;

    @Before
    public void setUp() throws Exception {
        inputForSelectingMenu = "0\n";

        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingMenu.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);

        menuList = new MenuList();
        menuList.add(new ListAllBook());
        menuList.add(new CheckOutBook());
        menuList.add(new Quit());

        bookList = new BookList();
        bookList.add(new Book(harryPotterAndPhilosophersStone, JKRowling, 1987));
        bookList.add(new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987));

        library = new Library(bookList, printer);

        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
    }

    @Test
    public void testPrintingOfWelcomeMessage() throws Exception, BookNotFoundException {
        String expectedWelcomeToBibliotecaMsg = ResourceBundle.getBundle("bibliotecaAppMessagesTest").getString("WelcomeMessage");

        bibliotecaApp.run(library);

        assertThat(outputStream.toString(),containsString(expectedWelcomeToBibliotecaMsg));
    }

    @Test
    public void testSeparatorBetweenWelcomeMessageAndListOfBooks() throws Exception, BookNotFoundException {
        bibliotecaApp.run(library);

        assertThat(outputStream.toString(), containsString("-----------------------------------------------------------------------------\n"));
    }

    @Test
    public void testMenuOptionOnStart() throws Exception, BookNotFoundException {
        String expectedMenuOptions = new String();
        expectedMenuOptions += "1. List Books";
        String selectOption = "Select Option: ";

        bibliotecaApp.run(library);

        assertThat(outputStream.toString(),containsString(expectedMenuOptions));
        assertThat(outputStream.toString(),containsString(selectOption));
    }

    @Test
    public void testSelectListBookOption() throws Exception, BookNotFoundException {
        inputForSelectingBook = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        assertThat(outputStream.toString(),containsString(harryPotterAndChambersOfSecrets));
        assertThat(outputStream.toString(),containsString(harryPotterAndPhilosophersStone));
    }

    @Test
    public void testSelectingInvalidOption() throws Exception, BookNotFoundException {
        inputForSelectingBook = "-1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "Invalid option!");

        assertThat(outputStream.toString(), is(expectedOutput));
    }

    @Test
    public void testSelectingOptionsUntilQuitOptionIsSelected() throws Exception, BookNotFoundException {
        inputForSelectingBook = "1\n-1\n3\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Invalid option!",
                "Book a week, keeps teacher away!");

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testSelectingToCheckOutABook() throws Exception, BookNotFoundException {
        inputForSelectingBook = "2\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Select a book: ",
                "Thanks you! Enjoy the book");

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOut() throws Exception, BookNotFoundException {
        inputForSelectingBook = "2\n3\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Select a book: ",
                "That book is not available.");

        assertThat(actualOutput,is(expectedOutput));
    }
}