package book;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBookCreation() throws Exception {
        Book book = new Book("A pedagogy of opression", "Paulo Freire", 1987);
        String expectedBookRepresentation = new String();
        expectedBookRepresentation += "|A pedagogy of opression                                         ";
        expectedBookRepresentation += "|Paulo Freire                    ";
        expectedBookRepresentation += "|1987";

        assertThat(book.toString(), is(expectedBookRepresentation));
    }
}