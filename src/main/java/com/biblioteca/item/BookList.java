package com.biblioteca.item;

public class BookList extends ItemList{
    @Override
    public String toString() {
        String result = new String();
        Book book;
        for(int i = 0; i < itemList.size(); i++) {
            book = (Book) itemList.get(i);
            if (book.isCheckedOut()) continue;
            result += book.toString() + "\n";
        }
        return result;
    }

    @Override
    public Book findFromAvailableItemsInStockById(String bookId) throws BookNotFoundException, BookIsNotAvailableForCheckOut {
        return (Book) super.findFromAvailableItemsInStockById(bookId);
    }

    @Override
    public Book findFromCheckedOutItemById(String index) throws BookNotFoundException, BookCanNotBeReturned {
        return (Book) super.findFromCheckedOutItemById(index);
    }
}
