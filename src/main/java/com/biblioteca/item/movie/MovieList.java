package com.biblioteca.item.movie;

import com.biblioteca.item.*;

public class MovieList extends ItemList {
    @Override
    protected Movie findFromAvailableItemsById(String movieId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        return (Movie) super.findFromAvailableItemsById(movieId);
    }

    @Override
    protected Movie findFromCheckedOutItemsById(String index) throws ItemNotFoundException, ItemCanNotBeReturned {
        return (Movie) super.findFromCheckedOutItemsById(index);
    }
}
