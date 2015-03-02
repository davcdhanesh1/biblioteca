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
    abstract T getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException;
    abstract String successMsg();
    abstract String invalidItemMsg();
    abstract String itemNotAvailableMsg();
    abstract void performAction(T item);
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

    public void checkOutBook(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {

        Closure<Book, BookList> Closure;
        Closure = new Closure<Book, BookList>(bookList) {

            @Override
            Book getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
                return bookList.findFromAvailableById(id);
            }

            @Override
            String successMsg() {
                return "Thanks you! Enjoy the book";
            }

            @Override
            String invalidItemMsg() {
                return "Invalid Book to checkout";
            }

            @Override
            String itemNotAvailableMsg() {
                return "That book is not available";
            }

            @Override
            void performAction(Book book) {
                book.checkOut();
            }
        };
        performAction(bookId, Closure);
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
        Closure<Movie, MovieList> Closure = new Closure<Movie, MovieList>(movieList) {

            @Override
            Movie getItem(String id) throws ItemIsNotAvailableForCheckOut, ItemNotFoundException {
                return movieList.findFromAvailableById(id);
            }

            @Override
            String successMsg() {
                return "Thanks you! Enjoy the movie";
            }

            @Override
            String invalidItemMsg() {
                return "Invalid Movie to checkout";
            }

            @Override
            String itemNotAvailableMsg() {
                return "That movie is not available";
            }

            @Override
            void performAction(Movie item) {
                item.checkOut();
            }
        };
        performAction(movieId, Closure);
    }

    private void performAction(String itemId, Closure Closure) {
        try {
            Item item = Closure.getItem(itemId);
            Closure.performAction(item);
            printer.println(Closure.successMsg());
        } catch (ItemNotFoundException e) {
            printer.println(Closure.invalidItemMsg());
        } catch (ItemIsNotAvailableForCheckOut e) {
            printer.println(Closure.itemNotAvailableMsg());
        }
    }

}
