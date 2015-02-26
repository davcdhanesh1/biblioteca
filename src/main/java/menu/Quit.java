package menu;

import IO.Printer;
import book.BookList;

public class Quit extends Menu {

    public Quit() {
        super("Quit");
    }

    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.println("Book a week, keeps teacher away!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return false;
    }
}
