package com.biblioteca;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.library.Library;
import com.biblioteca.menu.*;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BibliotecaAppTest {
    public static final String harryPotterAndPhilosophersStone = "Harry Potter and the Philosopher's Stone";
    public static final String harryPotterAndChambersOfSecrets = "Harry Potter and the Chamber of Secrets";
    private final String JKRowling = "J K Rowling";

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
    private Book harryPotterAndPhilosophersStoneBook;
    private Book harryPotterAndChambersOfSecretsBook;
    private MovieList movieList;

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
        menuList.add(new ReturnBook());
        menuList.add(new Quit());

        bookList = new BookList();
        harryPotterAndPhilosophersStoneBook = new Book(1, harryPotterAndPhilosophersStone, JKRowling, 1987);
        harryPotterAndChambersOfSecretsBook = new Book(2, BibliotecaAppTest.harryPotterAndChambersOfSecrets, JKRowling, 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);

        movieList = mock(MovieList.class);

        library = new Library(bookList, movieList, printer);

        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
    }

    @Test
    public void testSelectListBookOption() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ");

        assertThat(actual, is(expectedOutput));
    }

    @Test
    public void testSelectingInvalidOption() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Input can't be 0 or less than 0",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(), is(expectedOutput));
    }

    @Test
    public void testSelectingOptionsUntilQuitOptionIsSelected() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        inputForSelectingBook = "1\na\n5\n4\n";
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Input has to be number",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Invalid option!",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Book a week, keeps teacher away!",
                "-----------------------------------------------------------------------------"
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testSuccessfulBookCheckOut() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "Thanks you! Enjoy the book",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAnInvalidBookIsTriedToBeCheckedOut() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "Invalid Book to checkout",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAlreadyCheckedOutBookIsTriedToCheckedOut() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        harryPotterAndChambersOfSecretsBook.checkOut();

        inputForSelectingBook = "2\n2\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(new Library(bookList, movieList, printer));

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "That book is not available",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testSuccessfulBookReturn() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        harryPotterAndPhilosophersStoneBook.checkOut();
        inputForSelectingBook = "1\n3\n1\n1\n";
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "Thank you for returning the book.",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulBookReturnWhenInvalidBookIsTriedToBeReturned() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {

        inputForSelectingBook = "3\n10\n";
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "Invalid Book to return",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulBookReturnWhenAlreadyCheckedInBookIsTriedToBeCheckedIn() throws Exception, ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException {
        harryPotterAndPhilosophersStoneBook.checkIn();
        inputForSelectingBook = "3\n2\n";
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
                "3. Return a Book",
                "4. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "We already have this book !",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. Checkout a Book",
                "3. Return a Book",
                "4. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

}