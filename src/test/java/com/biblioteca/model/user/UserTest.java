package com.biblioteca.model.user;

import com.biblioteca.model.User;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.Rating;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {
    private User user;
    private Book book;
    private Movie movie;
    private User admin;

    @Before
    public void setUp() throws Exception {
        admin = User.admin("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        user = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        book = new Book(1, "A pedagogy of opression", "Paulo Freire", 1987);
        movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
    }

    @Test
    public void shouldReturnTrueIfUserMatches() throws Exception {
        String expectedUserString = "|777-4445|Dhanesh                         |davcdhanesh1@gmail.com          |9096904102";
        assertThat(user.toString(),is(expectedUserString));
    }

    @Test
    public void testIsAdmin() throws Exception {
        assertThat(admin.isAdmin(),is(true));
        assertThat(user.isAdmin(),is(false));
    }

}