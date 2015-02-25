import IO.Printer;
import book.BookList;

public abstract class Menu {

    public static void printAll(Printer printer) {
        printer.print("1. List Books");
    }

    public static Menu forOption(String option) {
        return new ListAllBooksMenu();
    }

    public abstract void perform(BookList bookList, Printer printer);
}
