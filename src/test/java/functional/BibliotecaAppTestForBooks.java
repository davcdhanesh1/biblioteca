package functional;

import com.biblioteca.BibliotecaApp;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.BorrowedItemList;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.Library;
import com.biblioteca.view.menu.MenuOptionList;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.model.User;
import com.biblioteca.model.UserList;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BibliotecaAppTestForBooks {
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
    private MenuOptionList menuOptionList;
    private Library library;
    private String inputForSelectingMenu;
    private Book harryPotterAndPhilosophersStoneBook;
    private Book harryPotterAndChambersOfSecretsBook;
    private MovieList movieList;
    private UserList userList;
    private User currentUser;
    private User dhanesh;
    private User frank;

    @Before
    public void setUp() throws Exception, InvalidLibraryAndPasswordCombination {
        currentUser = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        userList = new UserList();
        dhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        frank = User.customer("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);

        inputForSelectingMenu = "0\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingMenu.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);

        bookList = new BookList();
        harryPotterAndPhilosophersStoneBook = new Book(1, harryPotterAndPhilosophersStone, JKRowling, 1987);
        harryPotterAndChambersOfSecretsBook = new Book(2, BibliotecaAppTestForBooks.harryPotterAndChambersOfSecrets, JKRowling, 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);

        movieList = mock(MovieList.class);

        BorrowedItemList borrowedItemList = new BorrowedItemList();
        library = new Library(bookList, movieList, borrowedItemList);

        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
    }

    @Test
    public void testSelectListBookOption() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actual = outputStream.toString();
        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ");

        assertThat(actual, is(expectedOutput));
    }

    @Test
    public void testEnteringInvalidOptionFormat() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "-1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Input can't be 0 or less than 0",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(), is(expectedOutput));
    }

    @Test
    public void testSelectingOptionsUntilQuitOptionIsSelected() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "1\na\n10\n7\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Input has to be number",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Invalid option!",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Book a week, keeps teacher away!",
                "-----------------------------------------------------------------------------"
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testSuccessfulBookCheckOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "3\n777-4445\npassword\n1\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "Thanks you! Enjoy the book",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAnInvalidBookIsTriedToBeCheckedOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "3\n777-4445\npassword\n3\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "Invalid Book to checkout",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulCheckOutWhenAlreadyCheckedOutBookIsTriedToCheckedOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        harryPotterAndChambersOfSecretsBook.checkOut();

        inputForSelectingBook = "3\n777-4445\npassword\n2\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "",
                "Enter id of Book: ",
                "This book is not available",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSuccessfulBookReturn() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        harryPotterAndPhilosophersStoneBook.checkOut();
        inputForSelectingBook = "1\n5\n777-4445\npassword\n1\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "Thank you for returning the book",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Harry Potter and the Philosopher's Stone                        |J K Rowling                     |1987",
                "|2       |Harry Potter and the Chamber of Secrets                         |J K Rowling                     |1987",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void testUnsuccessfulBookReturnWhenInvalidBookIsTriedToBeReturned() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {

        inputForSelectingBook = "5\n777-4445\npassword\n10\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "Invalid Book to return",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

            assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUnsuccessfulBookReturnWhenAlreadyCheckedInBookIsTriedToBeCheckedIn() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        harryPotterAndPhilosophersStoneBook.checkIn();
        inputForSelectingBook = "5\n777-4445\npassword\n2\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "Enter id of Book: ",
                "We already have this book !",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testUserLoginWhenUseLoginIsUnSuccessfulSheShouldBeRedirectedToMainMenuAgain() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        inputForSelectingBook = "6\n777-4445\ninvalidPassword\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "Invalid Library Number or Password !",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }

    @Test
    public void testProfileInformationOptionWhenOnlyUserIsLoggedIntoSystem() throws Exception, ItemIsNotAvailableForCheckOut, InvalidLibraryAndPasswordCombination, InputValidationException, InvalidItemException, ItemCanNotBeReturned {
        inputForSelectingBook = "6\n777-4445\npassword\n6\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String actualOutput = outputStream.toString();

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Login",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|777-4445|Dhanesh                         |davcdhanesh1@gmail.com          |9096904102",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. Quit",
                "Select Option: "
        );

        assertThat(actualOutput,is(expectedOutput));
    }
}