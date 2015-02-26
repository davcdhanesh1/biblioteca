package menu;

import IO.Printer;
import book.BookList;

public class ListAllBook extends Menu{

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.println(bookList.toString());
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }
}
