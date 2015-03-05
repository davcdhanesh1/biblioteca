package com.biblioteca.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.menu.options.InvalidOption;
import com.biblioteca.menu.options.MenuOption;

import java.util.ArrayList;

public class MenuOptionList {

    private ArrayList<MenuOption> list = new ArrayList<MenuOption>();

    public void add(MenuOption menuOption) {
        list.add(menuOption);
    }

    public void printAll(Printer printer) {
        for(int index = 0; index < list.size(); index++) {
            printer.println((index + 1) + ". " + list.get(index).toString());
        }
    }

    public MenuOption find(String index) {
        int indexOfItemToBeFound = Integer.parseInt(index) - 1;
        try {
            return list.get(indexOfItemToBeFound);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidOption();
        }
    }
}
