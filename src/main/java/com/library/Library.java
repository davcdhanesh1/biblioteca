package com.library;

import com.io.Printer;
import com.book.Book;
import com.book.BookIsNotAvailable;
import com.book.BookList;
import com.book.BookNotFoundException;

public class Library {
    private final BookList bookList;
    private final Printer printer;

    public Library(BookList bookList, Printer printer) {

        this.bookList = bookList;
        this.printer = printer;
    }

    public void checkOut(String bookId) throws BookNotFoundException, BookIsNotAvailable {
        try {
            Book book = bookList.findFromAvailableBook(bookId);
            book.checkOut();
            printer.println("Thanks you! Enjoy the com.book");
        } catch (BookNotFoundException e) {
            printer.println(e.getMessage());
        } catch (BookIsNotAvailable e) {
            printer.println(e.getMessage());
        }
    }

    public void printAllBook() {
        printer.println(bookList.toString());
    }

    public void returnBook(String bookId) throws BookIsNotAvailable {
        try {
            Book book = bookList.findFromCheckedOutBooksById(bookId);
            book.checkIn();
            printer.println("Thank you for returning the com.book.");
        } catch (BookNotFoundException e) {
            printer.println(e.getMessage());
        } catch (BookIsNotAvailable e) {
            printer.println(e.getMessage());
        }
    }
}
