package com.biblioteca.model.rental.rentedItem;

import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.RentedItem;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.Rating;
import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RentedItemTest {

    private RentedItem borrowedMovie;
    private RentedItem borrowedBook;

    @Before
    public void setUp() throws Exception {
        User user = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
        Book book = new Book(1, "A pedagogy of opression", "Paulo Freire", 1987);
        borrowedMovie = new RentedItem(user, movie);
        borrowedBook = new RentedItem(user, book);
    }

    @Test
    public void testBorrowedItem() throws Exception {
        assertThat(borrowedMovie.description(), is("|777-4445|Dhanesh         |1       |Whiplash                        |Movie"));
        assertThat(borrowedBook.description(), is("|777-4445|Dhanesh         |1       |A pedagogy of opression         |Book"));
    }
}