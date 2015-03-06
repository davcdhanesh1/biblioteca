package com.biblioteca.model.rental.borrowedItem;

import com.biblioteca.model.rental.Book;
import com.biblioteca.model.User;
import com.biblioteca.model.rental.BorrowedItem;
import com.biblioteca.model.rental.BorrowedItemList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.mock;

public class BorrowedItemListTest {
    @Test
    public void testAdd() throws Exception {
        User user = mock(User.class);
        Book book = mock(Book.class);
        BorrowedItem borrowedItem = new BorrowedItem(user, book);
        BorrowedItemList borrowedItemList = new BorrowedItemList();

        borrowedItemList.add(borrowedItem);

        assertThat(borrowedItemList.getAllItems(),hasItem(borrowedItem));
    }

    @Test
    public void testRemove() throws Exception {
        User user = mock(User.class);
        Book book = mock(Book.class);
        BorrowedItem borrowedItem = new BorrowedItem(user, book);
        BorrowedItemList borrowedItemList = new BorrowedItemList();
        borrowedItemList.add(borrowedItem);
        borrowedItemList.remove(borrowedItem);
        assertThat(borrowedItemList.getAllItems().contains(borrowedItem), is(false));
    }
}