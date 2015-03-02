package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.*;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;

abstract class Closure<T extends Item, S extends ItemList> {
    private S list;

    Closure(S list) {
        this.list = list;
    }
    abstract T getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException, ItemCanNotBeReturned;
    abstract String successMsg();
    abstract String itemNotFoundMsg();
    abstract String actionCanNotBePerformedMsg();
    abstract void performAction(T item);

    public String perform(String itemId) throws ItemCanNotBeReturned {
        try {
            Item item = getItem(itemId);
            performAction((T) item);
            return successMsg();
        } catch (ItemNotFoundException e) {
            return itemNotFoundMsg();
        } catch (ItemIsNotAvailableForCheckOut|ItemCanNotBeReturned e) {
            return actionCanNotBePerformedMsg();
        }
    }
};

public class Library {
    private final BookList bookList;
    private final Printer printer;
    private final MovieList movieList;

    public Library(BookList bookList, MovieList movieList, Printer printer) {

        this.bookList = bookList;
        this.movieList = movieList;
        this.printer = printer;
    }

    public void checkOutBook(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned {

        Closure<Book, BookList> closure;
        closure = new Closure<Book, BookList>(bookList) {

            @Override
            Book getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
                return bookList.findFromAvailableById(id);
            }

            @Override
            String successMsg() {
                return "Thanks you! Enjoy the book";
            }

            @Override
            String itemNotFoundMsg() {
                return "Invalid Book to checkout";
            }

            @Override
            String actionCanNotBePerformedMsg() {
                return "That book is not available";
            }

            @Override
            void performAction(Book book) {
                book.checkOut();
            }
        };
        printer.println(closure.perform(bookId));
    }

    public void printAllBook() {
        printer.println(bookList.toString());
    }

    public void returnBook(String bookId) throws ItemNotFoundException, ItemCanNotBeReturned {
        Closure<Book, BookList> closure = new Closure<Book, BookList>(bookList) {
            @Override
            Book getItem(String id) throws ItemNotFoundException, ItemCanNotBeReturned {
                return bookList.findFromCheckedOutById(id);
            }

            @Override
            String successMsg() {
                return "Thank you for returning the book.";
            }

            @Override
            String itemNotFoundMsg() {
                return "Invalid Book to return";
            }

            @Override
            String actionCanNotBePerformedMsg() {
                return "We already have this book !";
            }

            @Override
            void performAction(Book book) {
                book.checkIn();
            }
        };

        printer.println(closure.perform(bookId));
    }

    public void printAllMovies() {
        printer.println(movieList.toString());
    }

    public void checkOutMovie(String movieId) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException, ItemCanNotBeReturned {
        Closure<Movie, MovieList> closure = new Closure<Movie, MovieList>(movieList) {

            @Override
            Movie getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
                return movieList.findFromAvailableById(id);
            }

            @Override
            String successMsg() {
                return "Thanks you! Enjoy the movie";
            }

            @Override
            String itemNotFoundMsg() {
                return "Invalid Movie to checkout";
            }

            @Override
            String actionCanNotBePerformedMsg() {
                return "That movie is not available";
            }

            @Override
            void performAction(Movie item) {
                item.checkOut();
            }
        };
        printer.println(closure.perform(movieId));
    }

}
