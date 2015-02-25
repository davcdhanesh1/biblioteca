package menu;

import IO.Printer;
import book.BookList;

public enum MenuOption {

    ListAllBook("1", "List Books") {
        @Override
        public void perform(BookList bookList, Printer printer) {
            printer.print(bookList.toString());
        }
    };

    private final String id;

    private final String description;

    MenuOption(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public abstract void perform(BookList bookList, Printer printer);

    public void view(Printer printer) {
        printer.print(id + ". " + description);
    }

    public static void printAll(Printer printer){
        for(MenuOption menuOption: MenuOption.values()) {
            menuOption.view(printer);
        }
    }

    public static MenuOption forOption(String option) {
        for(MenuOption menuOption: MenuOption.values()) {
            if(menuOption.matches(option)) {
                return menuOption;
            }
        }
        return null;
    }

    private boolean matches(String id) {
        return this.id.equals(id);
    }
}
