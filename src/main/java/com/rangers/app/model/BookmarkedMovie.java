package com.rangers.app.model;

public class BookmarkedMovie {
    private final int id;
    private final String title;
    private final String releaseDate;

    public BookmarkedMovie(int id, String title, String releaseDate) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
}
