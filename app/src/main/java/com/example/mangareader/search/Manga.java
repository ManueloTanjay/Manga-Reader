package com.example.mangareader.search;

public class Manga {

    private String title;
    private String id;
    private String description;

    Manga(String title, String id, String description) {
        this.title = title;
        this.id = id;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
