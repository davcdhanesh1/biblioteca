package com.book;

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
        int indexToDisplay; Book book;
        for(int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            if (book.isCheckedOut()) continue;
            indexToDisplay = i + 1;
            result += (indexToDisplay) + ". " + book.toString() + "\n";
        }
        return result;
    }

    public Book findFromAvailableBook(String index) throws BookNotFoundException, BookIsNotAvailable {
        int indexOfItemToBeFound = Integer.parseInt(index) - 1;
        try {
            Book book = bookList.get(indexOfItemToBeFound);
            if (book.isCheckedOut()) throw new BookIsNotAvailable("That com.book is not available");
            return book;
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Invalid Book to checkout");
        }
    }

    public Book findFromCheckedOutBooksById(String index) throws BookNotFoundException, BookIsNotAvailable {
        int indexOfItemToBeFound = Integer.parseInt(index) - 1;
        try {
            Book book = bookList.get(indexOfItemToBeFound);
            if (!book.isCheckedOut()) throw new BookIsNotAvailable("That com.book is not checked out");
            return book;
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Invalid Book to return");
        }
    }
}
