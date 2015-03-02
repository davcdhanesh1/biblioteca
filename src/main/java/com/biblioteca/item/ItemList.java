package com.biblioteca.item;

import java.util.ArrayList;

public abstract class ItemList {

    protected ArrayList<Item> itemList = new ArrayList<Item>();

    public void add(Item item) {
        itemList.add(item);
    }

    public Integer count() {
        return this.itemList.size();
    }

    @Override
    public String toString() {
        String result = new String();
        Item item;
        for(int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i);
            if (item.isCheckedOut()) continue;
            result += item.toString() + "\n";
        }
        return result;
    }

    protected Item findFromAvailableItemsInStockById(String bookId) throws BookNotFoundException, BookIsNotAvailableForCheckOut {
        int idOfBookToBeFound = Integer.parseInt(bookId);
        try {
            Item item = findItemWithId(idOfBookToBeFound);
            if (item.isCheckedOut()) throw new BookIsNotAvailableForCheckOut();
            return item;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException();
        }
    }

    protected Item findFromCheckedOutItemById(String index) throws BookNotFoundException, BookCanNotBeReturned {
        int idOfBookToBeFound = Integer.parseInt(index);
        try {
            Item item = findItemWithId(idOfBookToBeFound);
            if (!item.isCheckedOut()) throw new BookCanNotBeReturned();
            return item;
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException();
        }
    }

    private Item findItemWithId(int bookId) throws BookNotFoundException {
        for(Item item : itemList) {
            if (item.hasId(bookId)) {
                return item;
            }
        }
        throw new BookNotFoundException();
    }
}
