package com.biblioteca.model.rental;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;

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
