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
        int indexToDisplay; Book book;
        for(int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            if (book.isCheckedOut()) continue;
            indexToDisplay = i + 1;
            result += (indexToDisplay) + ". " + book.toString() + "\n";
        }
        return result;
    }
}
