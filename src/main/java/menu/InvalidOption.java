package menu;

import IO.Printer;
import book.BookList;

public class InvalidOption extends Menu {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.print("Invalid option!");
    }
}
