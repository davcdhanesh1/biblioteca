package book;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookListTest {

    @Test
    public void testListOfBooks() throws Exception {
        BookList bookList = new BookList();
        bookList.add(new Book("Harry Potter and the Philosopher's Stone"));
        bookList.add(new Book("Harry Potter and the Chamber of Secrets"));

        assertThat(bookList.count(), is(2));
    }
}