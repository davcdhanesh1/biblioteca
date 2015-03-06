package com.biblioteca.item.movie;

import com.biblioteca.item.Item;

public class Movie extends Item {
    private final String directorName;
    private final int publicationYear;
    private final Rating rating;

    public Movie(int id, String name, String directorName, int publicationYear, Rating rating) {
        super(id, name);
        this.directorName = directorName;
        this.publicationYear = publicationYear;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("|%-8d|%-64s|%-32s|%d|%s", id, name, directorName, publicationYear, rating.toString());
    }

    @Override
    public String description() {
        return super.description() + "|Movie";
    }
}
