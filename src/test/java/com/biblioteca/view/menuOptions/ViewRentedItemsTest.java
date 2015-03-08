package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;
import com.biblioteca.model.Library;
import com.biblioteca.model.User;
import com.biblioteca.model.UserSession;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.Rating;
import com.biblioteca.model.rental.RentedItem;
import com.biblioteca.view.View;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class ViewRentedItemsTest {

    private ViewRentedItems viewRentedItems;
    private Scanner mockScanner;
    private ByteArrayOutputStream byteArrayOutputStream;
    private Printer mockPrinter;
    private User userDhanesh;
    private RentedItem borrowedMovie;
    private Movie movie;
    private Book book;
    private RentedItem borrowedBook;
    private User userFoo;
    private UserSession mockUserSession;
    private View view;
    private Library mockLibrary;

    @Before
    public void setUp() throws Exception {
        viewRentedItems = new ViewRentedItems();
        mockScanner = new Scanner(System.in);
        mockPrinter = mock(Printer.class);
        view = new View(mockPrinter, mockScanner);

        userDhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        userFoo = User.customer("777-4446", "Foo", "password", "davcdhanesh1@gmail.com", "9422084738");

        movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
        book = new Book(1, "A pedagogy of opression", "Paulo Freire", 1987);
        borrowedMovie = new RentedItem(userDhanesh, movie);
        borrowedBook = new RentedItem(userFoo, book);

        mockUserSession = mock(UserSession.class);
        mockLibrary = mock(Library.class);
    }

    @Test
    public void testPerform() throws Exception {
        viewRentedItems.perform(mockUserSession, mockLibrary, view);

        verify(mockLibrary, times(1)).getRentedItemList();
    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(viewRentedItems.shouldContinueRunning(), is(true));
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(viewRentedItems.isSecureLoginRequired(), is(true));
    }
}