package com.biblioteca.item;

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

    public Book findFromAvailableBookById(String bookId) throws BookNotFoundException, BookIsNotAvailableForCheckOut {
        int idOfBookToBeFound = Integer.parseInt(bookId);
        try {
            Book book = findBookWithId(idOfBookToBeFound);
            if (book.isCheckedOut()) throw new BookIsNotAvailableForCheckOut();
            return book;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException();
        }
    }

    public Book findFromCheckedOutBooksById(String index) throws BookNotFoundException, BookCanNotBeReturned {
        int idOfBookToBeFound = Integer.parseInt(index);
        try {
            Book book = findBookWithId(idOfBookToBeFound);
            if (!book.isCheckedOut()) throw new BookCanNotBeReturned();
            return book;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException();
        }
    }

    private Book findBookWithId(int bookId) throws BookNotFoundException {
        for(Book book : bookList) {
            if (book.hasId(bookId)) {
                return book;
            }
        }
        throw new BookNotFoundException();
    }
}
