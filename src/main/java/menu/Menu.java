package menu;

import IO.Printer;
import book.BookList;

public enum Menu {

    ListAllBook("1", "List Books") {
        @Override
        public void perform(BookList bookList, Printer printer) {
            printer.print(bookList.toString());
        }
    };

    private final String id;

    private final String description;

    Menu(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public abstract void perform(BookList bookList, Printer printer);

    public void view(Printer printer) {
        printer.print(id + ". " + description);
    }

    public static void printAll(Printer printer){
        for(Menu menu : Menu.values()) {
            menu.view(printer);
        }
    }

    public static Menu forOption(String option) {
        for(Menu menu : Menu.values()) {
            if(menu.matches(option)) {
                return menu;
            }
        }
        return null;
    }

    private boolean matches(String id) {
        return this.id.equals(id);
    }
}
