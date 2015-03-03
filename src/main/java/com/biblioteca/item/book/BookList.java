package com.biblioteca.item.book;

import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemList;
import com.biblioteca.item.InvalidItemException;

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
