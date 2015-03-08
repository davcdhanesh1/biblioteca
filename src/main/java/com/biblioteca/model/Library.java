package com.biblioteca.model;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.RentedItem;
import com.biblioteca.model.rental.RentedItemList;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.MovieList;

public class Library {
    private final BookList bookList;
    private RentedItemList rentedItemList;
    private final MovieList movieList;

    public Library(BookList bookList, MovieList movieList, RentedItemList rentedItemList) {

        this.bookList = bookList;
        this.movieList = movieList;
        this.rentedItemList = rentedItemList;
    }

    public String checkOutBook(String bookId, final UserSession userSession) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        try {
            Book book = bookList.findFromAvailableById(bookId);
            book.checkOut();
            rentedItemList.add(new RentedItem(userSession.getCurrentUser(), book));
            return "Thanks you! Enjoy the book";
        } catch (InvalidItemException e) {
            return "Invalid Book to checkout";
        } catch (ItemIsNotAvailableForCheckOut e) {
            return "This book is not available";
        }
    }

    public String checkOutMovie(String movieId, final UserSession userSession) throws ItemIsNotAvailableForCheckOut, InvalidItemException, ItemCanNotBeReturned {
        try {
            Movie movie = movieList.findFromAvailableById(movieId);
            movie.checkOut();
            rentedItemList.add(new RentedItem(userSession.getCurrentUser(), movie));
            return "Thanks you! Enjoy the movie";
        } catch (InvalidItemException e) {
            return "Invalid Movie to checkout";
        } catch (ItemIsNotAvailableForCheckOut e) {
            return "This movie is not available";
        }
    }

    public String returnBook(String bookId, final UserSession userSession) throws InvalidItemException, ItemCanNotBeReturned {
        try {
            Book book = bookList.findFromCheckedOutById(bookId);
            book.checkIn();
            rentedItemList.remove(new RentedItem(userSession.getCurrentUser(), book));
            return "Thank you for returning the book";
        } catch (InvalidItemException e) {
            return "Invalid Book to return";
        } catch (ItemCanNotBeReturned e) {
            return "We already have this book !";
        }
    }

    public String getAllBooks() {
        return bookList.toString();
    }

    public String getAllMovies() {
        return movieList.toString();
    }

    public String getRentedItemList() {
        return rentedItemList.getAllDescription();
    }
}
