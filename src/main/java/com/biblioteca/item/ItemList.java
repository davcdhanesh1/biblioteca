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

    protected Item findFromAvailableItemsById(String bookId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        int idOfBookToBeFound = Integer.parseInt(bookId);
        try {
            Item item = findItemWithId(idOfBookToBeFound);
            if (item.isCheckedOut()) throw new ItemIsNotAvailableForCheckOut();
            return item;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
    }

    protected Item findFromCheckedOutItemsById(String index) throws ItemNotFoundException, ItemCanNotBeReturned {
        int idOfBookToBeFound = Integer.parseInt(index);
        try {
            Item item = findItemWithId(idOfBookToBeFound);
            if (!item.isCheckedOut()) throw new ItemCanNotBeReturned();
            return item;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
    }

    private Item findItemWithId(int bookId) throws ItemNotFoundException {
        for(Item item : itemList) {
            if (item.hasId(bookId)) {
                return item;
            }
        }
        throw new ItemNotFoundException();
    }
}
