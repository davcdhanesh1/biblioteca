package book;

public class BookIsNotAvailable extends Throwable {
    public BookIsNotAvailable(String s) {
        super(s);
    }
}
