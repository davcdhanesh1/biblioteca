package com.biblioteca.view;

import com.biblioteca.view.menuOptions.*;
import com.biblioteca.model.UserSession;

public class MenuListView {
    public MenuOptionList menuOptionList;

    private MenuListView(MenuOptionList menuOptionList) {
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

    private static MenuOptionList adminMenuOptionList() {
        MenuOptionList menuOptionList = new MenuOptionList();
        menuOptionList.add(new ListAllBook());
        menuOptionList.add(new ListAllMovies());
        menuOptionList.add(new CheckOutBook());
        menuOptionList.add(new CheckOutMovie());
        menuOptionList.add(new ReturnBook());
        menuOptionList.add(new ProfileInfo());
        menuOptionList.add(new ViewRentedItems());
        menuOptionList.add(new Quit());

        return menuOptionList;
    }

    public static MenuListView getCurrentMenuList(UserSession userSession) {
        if (userSession.getCurrentUser() == null) return new MenuListView(defaultMenuOptionList());
        if (userSession.getCurrentUser().isAdmin()) return new MenuListView(adminMenuOptionList());
        return new MenuListView(menuViewWithProfileInformation());
    }
}
