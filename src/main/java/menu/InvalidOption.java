package menu;

import IO.Printer;
import book.BookList;

public class InvalidOption extends Menu {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.println("Invalid option!");
    }
}
