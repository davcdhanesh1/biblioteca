package com.biblioteca.view;

import com.biblioteca.view.menu.MenuOptionList;
import com.biblioteca.view.menu.options.*;
import com.biblioteca.model.UserSession;

public class ViewState {
    public MenuOptionList menuOptionList;

    private ViewState(MenuOptionList menuOptionList) {
        this.menuOptionList = menuOptionList;
    }
    private static MenuOptionList defaultMenuOptionList() {
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new ListAllMovies());
        menuOptionList.add(new CheckOutBook());
        menuOptionList.add(new CheckOutMovie());
        menuOptionList.add(new ReturnBook());
        menuOptionList.add(new Login());
        menuOptionList.add(new Quit());

        return menuOptionList;
    }

    private static MenuOptionList menuViewWithProfileInformation() {
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new ListAllMovies());
        menuOptionList.add(new CheckOutBook());
        menuOptionList.add(new CheckOutMovie());
        menuOptionList.add(new ReturnBook());
        menuOptionList.add(new ProfileInfo());
        menuOptionList.add(new Quit());

        return menuOptionList;
    }

    public static ViewState getCurrentView(UserSession userSession) {
        if (userSession.getCurrentUser() == null)
            return new ViewState(defaultMenuOptionList());

        return new ViewState(menuViewWithProfileInformation());
    }
}
