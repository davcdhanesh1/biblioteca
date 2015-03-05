package com.biblioteca;

import com.biblioteca.menu.MenuOptionList;
import com.biblioteca.menu.options.*;

public class ViewState {
    public MenuOptionList menuOptionList;

    private ViewState(MenuOptionList menuOptionList) {
        this.menuOptionList = menuOptionList;
    }
    public static ViewState defaultMenuView() {
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new ListAllMovies());
        menuOptionList.add(new CheckOutBook());
        menuOptionList.add(new CheckOutMovie());
        menuOptionList.add(new ReturnBook());
        menuOptionList.add(new Login());
        menuOptionList.add(new Quit());

        return new ViewState(menuOptionList);
    }

    public static ViewState menuViewWithProfileInformation() {
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new ListAllMovies());
        menuOptionList.add(new CheckOutBook());
        menuOptionList.add(new CheckOutMovie());
        menuOptionList.add(new ReturnBook());
        menuOptionList.add(new ProfileInfo());
        menuOptionList.add(new Quit());

        return new ViewState(menuOptionList);
    }
}
