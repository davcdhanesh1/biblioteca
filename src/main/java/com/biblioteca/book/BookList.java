package com.biblioteca.book;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public void add(Book book) {
        bookList.add(book);
    }

    public Integer count() {
        return this.bookList.size();
    }

    @Override
    public String toString() {
        String result = new String();
        Book book;
        for(int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            if (book.isCheckedOut()) continue;
            result += book.toString() + "\n";
        }
        return result;
    }

    public Book findFromAvailableBookById(String bookId) throws BookNotFoundException, BookIsNotAvailable {
        int idOfBookToBeFound = Integer.parseInt(bookId);
        try {
            Book book = findBookWithId(idOfBookToBeFound);
            if (book.isCheckedOut()) throw new BookIsNotAvailable("That book is not available");
            return book;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Invalid Book to checkout");
        }
    }

    public Book findFromCheckedOutBooksById(String index) throws BookNotFoundException, BookIsNotAvailable {
        int idOfBookToBeFound = Integer.parseInt(index);
        try {
            Book book = findBookWithId(idOfBookToBeFound);
            if (!book.isCheckedOut()) throw new BookIsNotAvailable("That book is not checked out");
            return book;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Invalid Book to return");
        }
    }

    private Book findBookWithId(int bookId) throws BookNotFoundException {
        for(Book book : bookList) {
            if (book.hasId(bookId)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with given id is not present in the list of books");
    }
}
