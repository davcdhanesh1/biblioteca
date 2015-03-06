package com.biblioteca.model.rental.movie;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.*;
import org.junit.Before;
import org.junit.Test;
import testhelpers.StringUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieListTest {

    private MovieList movieList;
    private Movie whiplashMovie;
    private Movie birdmanMovie;

    @Before
    public void setUp() throws Exception {
        whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList = new MovieList();
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);
    }

    @Test
    public void testMovieListCreation() throws Exception {
        assertThat(movieList.count(), is(2));
    }

    @Test
    public void testPrintListOfMovies() throws Exception {
        String expectedMovieList = StringUtil.getOutputString(
                "|1       |Whiplash                                                        |Damien Chazelle                 |2014|NINE",
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN"
        );

        assertThat(movieList.toString(),is(expectedMovieList));
    }

    @Test
    public void testPrintListOfAllUnCheckedOutBooks() throws Exception {
        whiplashMovie.checkOut();
        String expectedMovieList = StringUtil.getOutputString(
                "|2       |BirdMan                                                         |Alejandro González Iñárritu     |2014|TEN"
        );

        assertThat(movieList.toString(),is(expectedMovieList));
    }

    @Test
    public void testFindFromAvailableMovies() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException {
        assertThat(movieList.findFromAvailableById("1"),is(whiplashMovie));
    }

    @Test(expected = InvalidItemException.class)
    public void testFindFromAvailableMoviesWhenMovieToBeFoundIsNotPresentInTheList() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException {
        movieList.findFromAvailableById("3");
    }

    @Test(expected = ItemIsNotAvailableForCheckOut.class)
    public void testFindingFromAvailableMoviesWhenMovieToBeFoundIsAlreadyCheckedOut() throws Exception, ItemIsNotAvailableForCheckOut, InvalidItemException {
        whiplashMovie.checkOut();
        movieList.findFromAvailableById("1");
    }

    @Test
    public void testFindFromCheckedOutBooks() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        whiplashMovie.checkOut();
        assertThat(movieList.findFromCheckedOutById("1"),is(whiplashMovie));
    }

    @Test(expected = InvalidItemException.class)
    public void testFindFromCheckedOutBooksWhenBookToBeCheckedOutIsNotPresentInTheList() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        movieList.findFromCheckedOutById("3");
    }

    @Test(expected = ItemCanNotBeReturned.class)
    public void testFindFromCheckedOutBooksWhenBookToBeFoundIsNotPresentInTheListOfCheckedOutBooks() throws Exception, InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        whiplashMovie.checkIn();
        movieList.findFromCheckedOutById("1");
    }
}