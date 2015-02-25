import IO.Printer;
import book.BookList;

public class ListAllBooksMenu extends Menu {
    @Override
    public void perform(BookList bookList, Printer printer) {
        printer.print(bookList.toString());
    }
}
