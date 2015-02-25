package book;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> list = new ArrayList<Book>();

    public void add(Book book) {
        list.add(book);
    }

    public Integer count() {
        return this.list.size();
    }
}
