package com.biblioteca.model.rental.movie;

import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.Rating;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MovieTest {

    @Test
    public void testMovieCreation() throws Exception {
        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
        String expectedMovieRepresentation = new String();
        expectedMovieRepresentation += "|1       ";
        expectedMovieRepresentation += "|Whiplash                                                        ";
        expectedMovieRepresentation += "|Damien Chazelle                 ";
        expectedMovieRepresentation += "|2014";
        expectedMovieRepresentation += "|TEN";

        assertThat(movie.toString(), is(expectedMovieRepresentation));
    }

    @Test
    public void testMovieCheckOut() throws Exception {
        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);

        assertThat(movie.isCheckedOut(),is(false));
        movie.checkOut();
        assertThat(movie.isCheckedOut(),is(true));
    }

    @Test
    public void testMovieCheckIn() throws Exception {
        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);

        movie.checkOut();
        movie.checkIn();
        assertThat(movie.isCheckedOut(),is(false));

    }
}