package com.biblioteca.rental;

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

    protected Item findFromAvailableById(String bookId) throws InvalidItemException, ItemIsNotAvailableForCheckOut {
        int idOfBookToBeFound = Integer.parseInt(bookId);
        Item item = findItemWithId(idOfBookToBeFound);
        if (item.isCheckedOut()) throw new ItemIsNotAvailableForCheckOut();
        return item;
    }

    protected Item findFromCheckedOutById(String index) throws InvalidItemException, ItemCanNotBeReturned {
        int idOfBookToBeFound = Integer.parseInt(index);
        Item item = findItemWithId(idOfBookToBeFound);
        if (!item.isCheckedOut()) throw new ItemCanNotBeReturned();
        return item;
    }

    private Item findItemWithId(int bookId) throws InvalidItemException {
        for(Item item : itemList) {
            if (item.hasId(bookId)) {
                return item;
            }
        }
        throw new InvalidItemException();
    }
}
