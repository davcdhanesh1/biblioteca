package menu;

import IO.Printer;
import book.BookList;

public abstract class Menu {

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    public abstract void perform(BookList bookList, Printer printer);

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }
}
