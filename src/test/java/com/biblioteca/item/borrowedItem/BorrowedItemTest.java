package com.biblioteca.item.borrowedItem;

import com.biblioteca.item.book.Book;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BorrowedItemTest {

    private BorrowedItem borrowedMovie;
    private BorrowedItem borrowedBook;

    @Before
    public void setUp() throws Exception {
        User user = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
        Book book = new Book(1, "A pedagogy of opression", "Paulo Freire", 1987);
        borrowedMovie = new BorrowedItem(user, movie);
        borrowedBook = new BorrowedItem(user, book);
    }

    @Test
    public void testBorrowedItem() throws Exception {
        assertThat(borrowedMovie.description(), is("|777-4445|Dhanesh         |1       |Whiplash                        |Movie"));
        assertThat(borrowedBook.description(), is("|777-4445|Dhanesh         |1       |A pedagogy of opression         |Book"));
    }
}