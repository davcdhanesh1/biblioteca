import book.BookList;

import java.util.ResourceBundle;

public class BibliotecaApp {

    public void run(Printer printer, BookList bookList) {
        printer.print(getWelcomeToBibliotecaMsg());
        printer.print(bookList.toString());
    }

    private String getWelcomeToBibliotecaMsg() {
        return ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage");
    }
}
