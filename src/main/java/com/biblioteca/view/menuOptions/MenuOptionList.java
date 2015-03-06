package com.biblioteca.view.menuOptions;

import com.biblioteca.io.Printer;

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

    public void remove(String menuName) {
        int indexOfItemToBeRemoved = 0;
        for(int index = 0; index < list.size(); index++) {
            if(list.get(index).toString().equals(menuName)) {
                indexOfItemToBeRemoved = index;
                break;
            }
        }
        list.remove(indexOfItemToBeRemoved);
    }

    public boolean hasMenu(String menuName) {
        for(int index = 0; index < list.size(); index++) {
            if(list.get(index).toString().equals(menuName)) return true;
        }
        return false;
    }
}
