package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.*;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.borrowedItem.BorrowedItem;
import com.biblioteca.item.borrowedItem.BorrowedItemList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.session.UserSession;

public class Library {
    private final BookList bookList;
    private BorrowedItemList borrowedItemList;
    private final Printer printer;
    private final MovieList movieList;

    public Library(BookList bookList, MovieList movieList, BorrowedItemList borrowedItemList, Printer printer) {

        this.bookList = bookList;
        this.movieList = movieList;
        this.borrowedItemList = borrowedItemList;
        this.printer = printer;
    }

    public String checkOutBook(String bookId, final UserSession userSession) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {
        try {
            Book book = bookList.findFromAvailableById(bookId);
            book.checkOut();
            borrowedItemList.add(new BorrowedItem(userSession.getCurrentUser(), book));
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
            borrowedItemList.add(new BorrowedItem(userSession.getCurrentUser(), movie));
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
            borrowedItemList.remove(new BorrowedItem(userSession.getCurrentUser(), book));
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

}
