package com.biblioteca.model.rental.rentedItem;

import com.biblioteca.model.rental.*;
import com.biblioteca.model.User;
import org.junit.Test;
import testhelpers.StringUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertEquals;

public class RentedItemListTest {
    @Test
    public void testAdd() throws Exception {
        User user = mock(User.class);
        Book book = mock(Book.class);
        RentedItem rentedItem = new RentedItem(user, book);
        RentedItemList rentedItemList = new RentedItemList();

        rentedItemList.add(rentedItem);

        assertThat(rentedItemList.getAllItems(),hasItem(rentedItem));
    }

    @Test
    public void testRemove() throws Exception {
        User user = mock(User.class);
        Book book = mock(Book.class);
        RentedItem rentedItem = new RentedItem(user, book);
        RentedItemList rentedItemList = new RentedItemList();
        rentedItemList.add(rentedItem);
        rentedItemList.remove(rentedItem);
        assertThat(rentedItemList.getAllItems().contains(rentedItem), is(false));
    }

    @Test
    public void testToString() throws Exception {
        RentedItemList rentedItemList = new RentedItemList();

        User userDhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        User userFoo = User.customer("777-4446", "Foo", "password", "davcdhanesh1@gmail.com", "9422084738");

        Movie movie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.TEN);
        Book book = new Book(1, "A pedagogy of opression", "Paulo Freire", 1987);

        RentedItem rentedMovie = new RentedItem(userDhanesh, movie);
        RentedItem rentedBook = new RentedItem(userFoo, book);
        rentedItemList.add(rentedMovie);
        rentedItemList.add(rentedBook);

        String expectedOutput = StringUtil.getOutputString(
                "|777-4445|Dhanesh         |1       |Whiplash                        |Movie",
                "|777-4446|Foo             |1       |A pedagogy of opression         |Book"
        );

        String actual = rentedItemList.getAllDescription();

        assertEquals(expectedOutput, actual);
    }
}