package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity {

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title=%s&limit=100&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga=%s&limit=100&offset=%s&translatedLanguage=en";
    public String getBaseURL = "/at-home/server/%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    test button onclick to create a new activity from a button in a fragment
    public void nextActivity(View view) {
        Intent intent = new Intent(this, ChapterActivity.class);
        startActivity(intent);
    }
}