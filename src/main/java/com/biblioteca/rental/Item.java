package com.biblioteca.rental;

public abstract class Item {

    protected final int id;
    protected final String name;
    private boolean checkedOut;

    protected Item(int id, String name) {
        this.id = id;
        this.checkedOut = false;
        this.name = name;
    }


    public boolean isCheckedOut() { return checkedOut; }

    public void checkOut() { checkedOut = true; }

    public void checkIn() { checkedOut = false; }

    public boolean hasId(int bookId) {
        return this.id == bookId;
    }

    public String description() {
        return String.format("|%-8d|%-32s", id, name);
    }
}
