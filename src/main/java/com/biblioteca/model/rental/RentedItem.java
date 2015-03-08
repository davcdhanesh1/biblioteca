package com.biblioteca.model.rental;

import com.biblioteca.model.User;

public class RentedItem {
    private User user;
    private final Item item;

    public RentedItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public String description() {
        return user.description() + item.description();
    }
}
