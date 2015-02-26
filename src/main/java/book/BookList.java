package book;

import menu.InvalidOption;

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

    public Book find(String index) throws BookNotFoundException {
        int indexOfItemToBeFound = Integer.parseInt(index) - 1;
        try {
            return bookList.get(indexOfItemToBeFound);
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Book you are tyring to find, is not present.");
        }
    }
}
