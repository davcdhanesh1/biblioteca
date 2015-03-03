package com.biblioteca.item.movie;

import com.biblioteca.item.*;

public class MovieList extends ItemList {
    @Override
    public Movie findFromAvailableById(String movieId) throws InvalidItemException, ItemIsNotAvailableForCheckOut {
        return (Movie) super.findFromAvailableById(movieId);
    }

    @Override
    public Movie findFromCheckedOutById(String index) throws InvalidItemException, ItemCanNotBeReturned {
        return (Movie) super.findFromCheckedOutById(index);
    }
}
