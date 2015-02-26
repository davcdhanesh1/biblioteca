package menu;

import IO.Printer;
import book.BookList;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.println(bookList.toString());
        printer.println("Select a book: ");
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

}
