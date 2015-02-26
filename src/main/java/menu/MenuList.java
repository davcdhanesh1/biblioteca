package menu;

import IO.Printer;

import java.util.ArrayList;

public class MenuList {

    private ArrayList<Menu> list = new ArrayList<Menu>();

    public void add(Menu menu) {
        list.add(menu);
    }

    public void printAll(Printer printer) {
        for(int index = 0; index < list.size(); index++) {
            printer.print((index + 1) + ". " + list.get(index).toString());
        }
    }
}
