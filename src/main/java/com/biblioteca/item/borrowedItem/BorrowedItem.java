package com.biblioteca.item.borrowedItem;

import com.biblioteca.item.Item;
import com.biblioteca.user.User;

public class BorrowedItem {
    private User user;
    private final Item item;

    public BorrowedItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public String description() {
        return user.description() + item.description();
    }
}
