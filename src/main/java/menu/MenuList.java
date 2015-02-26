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
            printer.println((index + 1) + ". " + list.get(index).toString());
        }
    }

    public Menu find(String index) {
        int indexOfItemToBeFound = Integer.parseInt(index) - 1;
        try {
            return list.get(indexOfItemToBeFound);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidOption();
        }
    }
}
