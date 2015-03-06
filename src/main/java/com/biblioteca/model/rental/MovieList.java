package com.biblioteca.model.rental;

import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;

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
