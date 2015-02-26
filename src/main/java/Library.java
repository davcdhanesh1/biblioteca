import IO.Printer;
import book.Book;
import book.BookList;

public class Library {
    private final BookList bookList;
    private final Printer printer;

    public Library(BookList bookList, Printer printer) {

        this.bookList = bookList;
        this.printer = printer;
    }

    public void checkOut(Book book) {
        book.checkOut();
    }
}
