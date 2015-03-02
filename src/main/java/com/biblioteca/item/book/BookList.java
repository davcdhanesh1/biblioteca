package com.biblioteca.item.book;

import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemList;
import com.biblioteca.item.ItemNotFoundException;

public class BookList extends ItemList {

    @Override
    public Book findFromAvailableById(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        return (Book) super.findFromAvailableById(bookId);
    }

    @Override
    public Book findFromCheckedOutById(String index) throws ItemNotFoundException, ItemCanNotBeReturned {
        return (Book) super.findFromCheckedOutById(index);
    }
}
