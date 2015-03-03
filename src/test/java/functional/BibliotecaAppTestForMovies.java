package functional;

import com.biblioteca.BibliotecaApp;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.library.Library;
import com.biblioteca.menu.CheckOutMovie;
import com.biblioteca.menu.ListAllMovies;
import com.biblioteca.menu.MenuList;
import com.biblioteca.menu.Quit;
import com.biblioteca.user.InvalidUserPasswordCombination;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BibliotecaAppTestForMovies {


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
    private Movie whiplashMovie;
    private Movie birdmanMovie;
    private UserList userList;
    private User currentUser;

    @Before
    public void setUp() throws Exception, InvalidUserPasswordCombination {
        userList = mock(UserList.class);
        currentUser = new User("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        userList = mock(UserList.class);
        when(userList.findByLibraryNumberAndPassword("dhanesh", "password")).thenReturn(currentUser);


        inputForSelectingMenu = "0\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingMenu.getBytes());
        scanner = new Scanner(byteArrayInputStream);

        outputStream = new ByteArrayOutputStream();
        printer = new Printer(outputStream);

        menuList = new MenuList();
        menuList.add(new ListAllMovies());
        menuList.add(new CheckOutMovie());
        menuList.add(new Quit());

        bookList = mock(BookList.class);
        movieList = new MovieList();
        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        library = new Library(bookList, movieList, printer);

        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList,userList);
    }

    @Test
    public void testSelectionOfListOfMovies() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidUserPasswordCombination {

        inputForSelectingBook = "1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList, userList);
        bibliotecaApp.run(library);

        String expectedOutPut = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(),is(expectedOutPut));
    }

    @Test
    public void testSuccessfulCheckOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidUserPasswordCombination {
        inputForSelectingBook = "2\n1\n1";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList, userList);
        bibliotecaApp.run(library);

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "Thanks you! Enjoy the movie",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(),is(expectedOutput));
        assertThat(whiplashMovie.isCheckedOut(),is(true));
    }

    @Test
    public void testUnSuccessFulCheckOutWhenMovieToBeCheckedOutIsAlreadyCheckedOut() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidUserPasswordCombination {
        whiplashMovie.checkOut();
        inputForSelectingBook = "2\n1\n1\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList, userList);
        bibliotecaApp.run(library);

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "That movie is not available",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(),is(expectedOutput));
    }

    @Test
    public void testUnSuccessFulCheckOutWhenMovieToBeCheckedOutIsNotPresentInTheLibrary() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, InputValidationException, ItemCanNotBeReturned, InvalidUserPasswordCombination {
        inputForSelectingBook = "2\n10\n";
        byteArrayInputStream = new ByteArrayInputStream(inputForSelectingBook.getBytes());
        scanner = new Scanner(byteArrayInputStream);
        bibliotecaApp = new BibliotecaApp(printer, scanner, menuList, userList);
        bibliotecaApp.run(library);

        String expectedOutput = StringUtil.getOutputString(
                "Welcome To Biblioteca",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: ",
                "-----------------------------------------------------------------------------",
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN",
                "",
                "Enter id of Movie: ",
                "Invalid Movie to checkout",
                "-----------------------------------------------------------------------------",
                "1. List Movies",
                "2. Checkout a Movie",
                "3. Quit",
                "Select Option: "
        );

        assertThat(outputStream.toString(),is(expectedOutput));
    }
}
