package com.biblioteca.library;

import com.biblioteca.io.Printer;
import com.biblioteca.item.*;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;

public class Library {
    private final BookList bookList;
    private final Printer printer;

    public Library(BookList bookList, Printer printer) {

        this.bookList = bookList;
        this.printer = printer;
    }

    public void checkOut(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        try {
            Book book = bookList.findFromAvailableItemsById(bookId);
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
            Book book = bookList.findFromCheckedOutItemsById(bookId);
            book.checkIn();
            printer.println("Thank you for returning the book.");
        } catch (ItemNotFoundException e) {
            printer.println("Invalid Book to return");
        } catch (ItemCanNotBeReturned e) {
            printer.println("We already have this book !");
        }
    }
}
