package com.biblioteca.item.borrowedItem;

import com.biblioteca.item.book.Book;
import com.biblioteca.user.User;
import org.junit.Assert;
import org.junit.Test;

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

        try {
            assertThat(borrowedItemList.getAllItems(), hasItem(borrowedItem));
            Assert.fail("BorrowedItemList should have removed item");
        } catch (AssertionError e) {;}

    }
}