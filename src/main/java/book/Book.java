package book;

public class Book {

    private final String name;
    private final String author;
    private final int publicationYear;

    public Book(String name, String author, int publicationYear) {
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return String.format("|%-64s|%-32s|%d", name, author, publicationYear);
    }

}
