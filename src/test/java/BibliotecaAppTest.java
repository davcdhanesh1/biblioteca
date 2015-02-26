import IO.Printer;
import book.BookIsNotAvailable;
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
    public void testSelectListBookOption() throws Exception, BookNotFoundException, BookIsNotAvailable {
        inputForSelectingBook = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);

        String actual = outputStream.toString();
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
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ");

        assertThat(actual, is(expectedOutput));
    }

    @Test
    public void testSelectingInvalidOption() throws Exception, BookNotFoundException, BookIsNotAvailable {
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
                "Invalid option!",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(), is(expectedOutput));
    }

    @Test
    public void testSelectingOptionsUntilQuitOptionIsSelected() throws Exception, BookNotFoundException, BookIsNotAvailable {
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
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "Invalid option!",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "Book a week, keeps teacher away!");

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testSuccessfulBookCheckOut() throws Exception, BookNotFoundException, BookIsNotAvailable {
        inputForSelectingBook = "2\n1\n1\n";
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
                "Thanks you! Enjoy the book",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "2. |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAnInvalidBookIsTriedToBeCheckedOut() throws Exception, BookNotFoundException, BookIsNotAvailable {
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
                "Invalid Book to return",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAlreadyCheckedOutBookIsTriedToCheckedOut() throws Exception, BookNotFoundException, BookIsNotAvailable {
        BookList bookList = new BookList();
        Book harryPotterAndPhilosophersStoneBook = new Book(harryPotterAndPhilosophersStone, JKRowling, 1987);
        Book harryPotterAndChambersOfSecretsBook = new Book(harryPotterAndChambersOfSecrets, JKRowling, 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);
        harryPotterAndChambersOfSecretsBook.checkOut();

        inputForSelectingBook = "2\n2\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(new Library(bookList, printer));

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: ",
                "1. |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "",
                "Select a book: ",
                "That book is not available",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }
}