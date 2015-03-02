package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemNotFoundException;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;

public class Library {
    private final BookList bookList;
    private final Printer printer;
    private final MovieList movieList;

    public Library(BookList bookList, MovieList movieList, Printer printer) {

        this.bookList = bookList;
        this.movieList = movieList;
        this.printer = printer;
    }

    public void checkOutBook(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        try {
            Book book = bookList.findFromAvailableById(bookId);
            book.checkOut();
            printer.println("Thanks you! Enjoy the book");
        } catch (ItemNotFoundException e) {
            printer.println("Invalid Book to checkout");
        } catch (ItemIsNotAvailableForCheckOut e) {
            printer.println("That book is not available");
        }
    }

    public void printAllBook() {
        printer.println(bookList.toString());
    }

    public void returnBook(String bookId) throws ItemNotFoundException, ItemCanNotBeReturned {
        try {
            Book book = bookList.findFromCheckedOutById(bookId);
            book.checkIn();
            printer.println("Thank you for returning the book.");
        } catch (ItemNotFoundException e) {
            printer.println("Invalid Book to return");
        } catch (ItemCanNotBeReturned e) {
            printer.println("We already have this book !");
        }
    }

    public void printAllMovies() {
        printer.println(movieList.toString());
    }

    public void checkOutMovie(String movieId) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
        Movie movie = movieList.findFromAvailableById(movieId);
        movie.checkOut();
        printer.println("Thanks you! Enjoy the movie");
    }
}
