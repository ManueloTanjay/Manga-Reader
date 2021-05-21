package com.example.mangareader.chapters;

import java.util.ArrayList;

public class Chapter implements Comparable<Chapter>{
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


    @Override
    public int compareTo(Chapter o) {
        int vol_comp = Integer.valueOf(this.volume).compareTo(Integer.valueOf(o.volume));
        if (vol_comp == 0) {
            return Integer.valueOf(this.chapter).compareTo(Integer.valueOf(o.chapter));
        } else {
            return vol_comp;
        }
    }
}
