package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends FragmentActivity {

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title=%s&limit=100&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga={id}&limit=100&offset={offset}&translatedLanguage=en";
    public String getBaseURL = "/at-home/server/%s";

    public String naruto_id = "6b1eb93e-473a-4ab3-9922-1a66d2a29a4a";

    private Thread getMangaThread;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    test button onclick to create a new activity from a button in a fragment
    public void nextActivity(View view) {
        Intent intent = new Intent(this, ChapterActivity.class);

//        String query = baseURL + getChapters.replace("{id}", naruto_id).replace("{offset}", "0");

//        getMangaThread = new Thread(new getMangaRunnable());
//        getMangaThread.start();

        startActivity(intent);
    }
}