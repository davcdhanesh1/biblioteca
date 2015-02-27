package com.biblioteca.library;

import com.biblioteca.book.*;
import com.biblioteca.io.Printer;

public class Library {
    private final BookList bookList;
    private final Printer printer;

    public Library(BookList bookList, Printer printer) {

        this.bookList = bookList;
        this.printer = printer;
    }

    public void checkOut(String bookId) throws BookNotFoundException, BookIsNotAvailableForCheckOut {
        try {
            Book book = bookList.findFromAvailableBookById(bookId);
            book.checkOut();
            printer.println("Thanks you! Enjoy the book");
        } catch (BookNotFoundException e) {
            printer.println("Invalid Book to checkout");
        } catch (BookIsNotAvailableForCheckOut e) {
            printer.println("That book is not available");
        }
    }

    public void printAllBook() {
        printer.println(bookList.toString());
    }

    public void returnBook(String bookId) throws BookNotFoundException, BookCanNotBeReturned {
        try {
            Book book = bookList.findFromCheckedOutBooksById(bookId);
            book.checkIn();
            printer.println("Thank you for returning the book.");
        } catch (BookNotFoundException e) {
            printer.println("Invalid Book to return");
        } catch (BookCanNotBeReturned e) {
            printer.println("We already have this book !");
        }
    }
}
