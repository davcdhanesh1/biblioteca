package com.biblioteca.item.book;

import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.ItemList;
import com.biblioteca.item.ItemNotFoundException;

public class BookList extends ItemList {
    @Override
    public String toString() {
        String result = new String();
        Book book;
        for(int i = 0; i < itemList.size(); i++) {
            book = (Book) itemList.get(i);
            if (book.isCheckedOut()) continue;
            result += book.toString() + "\n";
        }
        return result;
    }

    @Override
    public Book findFromAvailableItemsInStockById(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        return (Book) super.findFromAvailableItemsInStockById(bookId);
    }

    @Override
    public Book findFromCheckedOutItemById(String index) throws ItemNotFoundException, ItemCanNotBeReturned {
        return (Book) super.findFromCheckedOutItemById(index);
    }
}
