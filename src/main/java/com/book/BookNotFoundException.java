package com.book;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
