package functional;

import com.biblioteca.BibliotecaApp;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.RentedItemList;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.rental.Rating;
import com.biblioteca.model.Library;
import com.biblioteca.view.menuOptions.MenuOptionList;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BibliotecaAppTestForMovies {


    BookList bookList;
    private ByteArrayOutputStream byteArrayOutputStream;
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

        byteArrayOutputStream = new ByteArrayOutputStream();
        printer = new Printer(byteArrayOutputStream);

        bookList = mock(BookList.class);
        movieList = new MovieList();
        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        RentedItemList rentedItemList = new RentedItemList();
        library = new Library(bookList, movieList, rentedItemList);

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

        assertThat(byteArrayOutputStream.toString(),is(expectedOutPut));
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

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
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

        assertEquals(expectedOutput, byteArrayOutputStream.toString());
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

        assertThat(byteArrayOutputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testViewRentedItemsAdminUI() throws Exception {
        User admin = User.admin("111-0000", "dhanesh", "password", "admin@bilioteca.com", "9422084738");
        userList.add(admin);

        inputForSelectingBook = "4\n111-0000\npassword\n1\n7";
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
                "7. View Rented Items",
                "8. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|111-0000|dhanesh         |1       |Whiplash                        |Movie",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Books",
                "2. List Movies",
                "3. Checkout a Book",
                "4. Checkout a Movie",
                "5. Return a Book",
                "6. Profile information",
                "7. View Rented Items",
                "8. Quit",
                "Select Option: "
        );

        assertEquals(expectedOutput, byteArrayOutputStream.toString());
    }
}
