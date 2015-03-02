package com.biblioteca.item;

public abstract class Item {

    protected final int id;
    private boolean checkedOut;

    protected Item(int id) {
        this.id = id;
        this.checkedOut = false;
    }


    public boolean isCheckedOut() { return checkedOut; }

    public void checkOut() { checkedOut = true; }

    public void checkIn() { checkedOut = false; }

    public boolean hasId(int bookId) {
        return this.id == bookId;
    }
}
