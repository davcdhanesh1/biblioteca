package functional;

import com.biblioteca.BibliotecaApp;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.borrowedItem.BorrowedItemList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.library.Library;
import com.biblioteca.menu.MenuOptionList;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BibliotecaAppTestForMovies {


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
    private Movie whiplashMovie;
    private Movie birdmanMovie;
    private UserList userList;
    private User currentUser;
    private User dhanesh;
    private User frank;

    @Before
    public void setUp() throws Exception, InvalidLibraryAndPasswordCombination {
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

        bookList = mock(BookList.class);
        movieList = new MovieList();
        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        BorrowedItemList borrowedItemList = new BorrowedItemList();
        library = new Library(bookList, movieList, borrowedItemList, printer);

    }

    @Test
    public void testSelectionOfListOfMovies() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {

        inputForSelectingBook = "2\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();

        String expectedOutPut = StringUtil.getOutputString(
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
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
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

        assertThat(outputStream.toString(),is(expectedOutPut));
    }

    @Test
    public void testSuccessfulCheckOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "4\n777-4445\npassword\n1\n2";
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
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "Thanks you! Enjoy the movie",
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
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
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

        assertThat(outputStream.toString(),is(expectedOutput));
        assertThat(whiplashMovie.isCheckedOut(),is(true));
    }

    @Test
    public void testUnSuccessFulCheckOutWhenMovieToBeCheckedOutIsAlreadyCheckedOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        whiplashMovie.checkOut();
        inputForSelectingBook = "4\n777-4445\npassword\n1\n2\n";
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
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "This movie is not available",
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
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
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

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUnSuccessFulCheckOutWhenMovieToBeCheckedOutIsNotPresentInTheLibrary() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidLibraryAndPasswordCombination {
        inputForSelectingBook = "4\n777-4445\npassword\n10\n";
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
                "Enter your library Number: ",
                "Enter your password: ",
                "-----------------------------------------------------------------------------",
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "Invalid Movie to checkout",
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

        assertThat(outputStream.toString(),is(expectedOutput));
    }
}
