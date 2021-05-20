package com.example.mangareader;

import java.util.ArrayList;

public class Chapter {
    public String volume;
    public String chapter;
    public String title;
    public String language;

    public String hash;
    public String id;
    public ArrayList<String> images_url;

    public Chapter() {
        this.images_url = new ArrayList<>();
    }
}
