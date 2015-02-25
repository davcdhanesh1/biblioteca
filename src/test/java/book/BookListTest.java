package book;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookListTest {
    public static final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE = "Harry Potter and the Philosopher's Stone";
    public static final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS = "Harry Potter and the Chamber of Secrets";
    BookList bookList;

    @Before
    public void setUp() throws Exception {
        bookList = new BookList();
        bookList.add(new Book(HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE));
        bookList.add(new Book(HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS));
    }

    @Test
    public void testListOfBooks() throws Exception {
        assertThat(bookList.count(), is(2));
    }

    @Test
    public void testPrintListOfBooks() throws Exception {
        String expectedBookListOutput = HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE + "\n" +
                                        HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS + "\n";
        assertThat(bookList.toString(),is(expectedBookListOutput));
    }
}