package book;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBookCreation() throws Exception {
        Book book = new Book("A pedagogy of opression");
        assertThat(book.toString(), is("A pedagogy of opression"));
    }
}