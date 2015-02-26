import IO.Printer;
import book.Book;
import book.BookList;
import book.BookNotFoundException;

public class Library {
    private final BookList bookList;
    private final Printer printer;

    public Library(BookList bookList, Printer printer) {

        this.bookList = bookList;
        this.printer = printer;
    }

    public void checkOut(String bookId) throws BookNotFoundException {
        try {
            Book book = bookList.find(bookId);
            book.checkOut();
        } catch (BookNotFoundException e) {
            printer.println(e.getMessage());
        }
    }
}
