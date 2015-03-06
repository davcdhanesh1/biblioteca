package com.biblioteca.rental.book;

import com.biblioteca.rental.ItemCanNotBeReturned;
import com.biblioteca.rental.ItemIsNotAvailableForCheckOut;
import com.biblioteca.rental.ItemList;
import com.biblioteca.rental.InvalidItemException;

public class BookList extends ItemList {

    @Override
    public Book findFromAvailableById(String bookId) throws InvalidItemException, ItemIsNotAvailableForCheckOut {
        return (Book) super.findFromAvailableById(bookId);
    }

    @Override
    public Book findFromCheckedOutById(String index) throws InvalidItemException, ItemCanNotBeReturned {
        return (Book) super.findFromCheckedOutById(index);
    }
}
