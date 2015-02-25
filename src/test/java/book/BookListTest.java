package book;

import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookListTest {
    public static final String HARRY_POTTER_AND_THE_PHILOSOPHERS_STONE
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndThePhilosophersStone");
    public static final String HARRY_POTTER_AND_THE_CHAMBER_OF_SECRETS
            = ResourceBundle.getBundle("bookList").getString("HarryPotterAndTheChambersOfSecrets");
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