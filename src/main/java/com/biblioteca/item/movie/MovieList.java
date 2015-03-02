package com.biblioteca.item.movie;

import com.biblioteca.item.*;

public class MovieList extends ItemList {
    @Override
    public Movie findFromAvailableById(String movieId) throws ItemNotFoundException, ItemIsNotAvailableForCheckOut {
        return (Movie) super.findFromAvailableById(movieId);
    }

    @Override
    public Movie findFromCheckedOutById(String index) throws ItemNotFoundException, ItemCanNotBeReturned {
        return (Movie) super.findFromCheckedOutById(index);
    }
}
