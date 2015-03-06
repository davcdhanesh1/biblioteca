package com.biblioteca.rental.borrowedItem;

import java.util.ArrayList;

public class BorrowedItemList {

    private ArrayList<BorrowedItem> list = new ArrayList<BorrowedItem>();

    public void add(BorrowedItem borrowedItem) {
        list.add(borrowedItem);
    }

    public ArrayList<BorrowedItem> getAllItems() {
        return list;
    }

    public void remove(BorrowedItem borrowedItem) {
        list.remove(borrowedItem);
    }

}
