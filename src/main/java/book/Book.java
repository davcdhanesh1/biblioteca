package book;

public class Book {

    private final String name;
    private final String author;
    private final int publicationYear;
    private boolean checkedOut;

    public Book(String name, String author, int publicationYear) {
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
        this.checkedOut = false;
    }

    @Override
    public String toString() {
        return String.format("|%-64s|%-32s|%d", name, author, publicationYear);
    }


    public boolean isCheckedOut() { return checkedOut; }

    public void checkOut() { checkedOut = true; }
}
