package com.biblioteca.model.rental;

import java.util.ArrayList;

public class RentedItemList {

    private ArrayList<RentedItem> list = new ArrayList<RentedItem>();

    public void add(RentedItem rentedItem) {
        list.add(rentedItem);
    }

    public ArrayList<RentedItem> getAllItems() {
        return list;
    }

    public void remove(RentedItem rentedItem) {
        list.remove(rentedItem);
    }

    public String getAllDescription() {
        StringBuilder sb = new StringBuilder();
        for(RentedItem rentedItem : list) {
            sb.append(rentedItem.description());
            sb.append("\n");
        }
        return sb.toString();
    }
}
