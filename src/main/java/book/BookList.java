package book;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public void add(Book book) {
        bookList.add(book);
    }

    public Integer count() {
        return this.bookList.size();
    }

    @Override
    public String toString() {
        String result = new String();
        for (Book book: bookList) {
            if(book.isCheckedOut()) continue;
            result += book.toString() + "\n";
        }
        return result;
    }
}
