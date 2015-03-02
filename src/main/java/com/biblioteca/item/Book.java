package com.biblioteca.item;

public class Book {

    private final int id;
    private final String name;
    private final String author;
    private final int publicationYear;
    private boolean checkedOut;

    public Book(int id, String name, String author, int publicationYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
        this.checkedOut =  false;
    }

    @Override
    public String toString() {
        return String.format("|%-8d|%-64s|%-32s|%d", id, name, author, publicationYear);
    }


    public boolean isCheckedOut() { return checkedOut; }

    public void checkOut() { checkedOut = true; }

    public void checkIn() { checkedOut = false; }

    public boolean hasId(int bookId) {
        return this.id == bookId;
    }
}
