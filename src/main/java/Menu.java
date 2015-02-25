import IO.Printer;
import book.BookList;

import java.util.ArrayList;

public abstract class Menu {

    protected final int id;

    private static final ArrayList<Menu> menuList = new ArrayList<Menu>();

    static {
        menuList.add(new ListAllBook(1));
    }

    public Menu(int id) {
        this.id = id;
    }

    public abstract void perform(BookList bookList, Printer printer);

    protected abstract void view(Printer printer);

    public static void printAll(Printer printer) {
        for(int i = 0; i < menuList.size(); i++) {
            menuList.get(i).view(printer);
        }
    }

    public static Menu forOption(String option) {
        return menuList.get(0);
    }

    private static class ListAllBook extends Menu {
        @Override
        protected void view(Printer printer) {
            printer.print(this.id + ". List Books");
        }

        @Override
        public void perform(BookList bookList, Printer printer) {
            printer.print(bookList.toString());
        }

        public ListAllBook(int id) {
            super(id);
        }
    }
}
