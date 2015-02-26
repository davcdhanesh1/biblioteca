package book;

import org.junit.Test;
import testhelpers.StringUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBookCreation() throws Exception {
        Book book = new Book("A pedagogy of opression", "Paulo Freire", 1987);
        String expectedBookRepresentation = StringUtil.getOutputString(
                "|A pedagogy of opression                                         |Paulo Freire                    |1987"
        );

        assertThat(book.toString(), is(expectedBookRepresentation));
    }

    @Test
    public void testCheckOutBook() throws Exception {
        Book book = new Book("A pedagogy of opression", "Paulo Freire", 1987);

        assertThat(book.isCheckedOut(), is(false));
        book.checkOut();
        assertThat(book.isCheckedOut(), is(true));
    }

    @Test
    public void testCheckInBook() throws Exception {
        Book book = new Book("A pedagogy of opression", "Paulo Freire", 1987);

        book.checkOut();
        book.checkIn();
        assertThat(book.isCheckedOut(), is(false));
    }
}