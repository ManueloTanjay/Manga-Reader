package com.example.mangareader.chapters;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.mangareader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ChapterActivity extends FragmentActivity {

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title=%s&limit=100;";
    //&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga={id}&limit=100&offset={offset}&translatedLanguage[]=en";
    public String getBaseURL = "/at-home/server/%s";

    // Fragments
    //public MangaChaptersFragment mangaChaptersFragment;

    // View element
    RecyclerView recyclerView;
    RecyclerViewAdapterChapters rvAdapter;
    TextView textView_title, textView_description, textView_genre;

    // Passing Data
    Bundle bundle;

    // Data
    public String naruto_id = "6b1eb93e-473a-4ab3-9922-1a66d2a29a4a";
    public ArrayList<Chapter> chapters = new ArrayList<>();
    public ArrayList<String> chapters_info = new ArrayList<>();
    public ArrayList<String> chapters_id = new ArrayList<>();
    public ArrayList<String> chapters_hash = new ArrayList<>();
    public ArrayList<String> loading = new ArrayList<>();

    private Thread getMangaThread;
    private InputStream inputStream;

    String manga_id;
    String manga_description;
    String title;
    String tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        textView_title = (TextView) findViewById(R.id.textView_title);
        textView_description = (TextView) findViewById(R.id.textView_description);
        textView_genre = (TextView) findViewById(R.id.textView_genre);

        loading.add("Loading...");
        // Set the display
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_chapters);
        rvAdapter = new RecyclerViewAdapterChapters(this, loading, loading);

        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textView_genre.setText("genres");
        getData();

        getMangaThread = new Thread(new GetMangaRunnable(this));
        getMangaThread.start();
    }

    private class GetMangaRunnable implements Runnable {
        Context ct;

        public GetMangaRunnable(Context ct) {
            this.ct = ct;
        }

        @Override
        public void run() {
            try {
                // The first query to get how many chapters there are for this manga
                String query = baseURL + getChapters.replace("{id}", manga_id).replace("{offset}", "0");
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                inputStream = conn.getInputStream();

                // Convert input stream to String
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";
                Log.v("DEBUG_TAG", result);

                // Parse JSON
                JSONObject jsonObject = new JSONObject(result);
                int totalChapters = jsonObject.getInt("total"); // the total number of chapters
                int countChapter = 0;
                Log.v("DEBUG_TAG", "i = " + countChapter + " total = " + totalChapters + " size = " + chapters.size());

                JSONArray results = jsonObject.getJSONArray("results");
                for (countChapter = 0; countChapter < results.length(); countChapter++) {
                    Chapter chapter = new Chapter();

                    JSONObject jsonChapterElem = results.getJSONObject(countChapter);
                    chapter.volume = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("volume");
                    chapter.chapter = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("chapter");
                    if (chapter.chapter.equals("null"))
                        chapter.chapter = "0";

                    chapter.title = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("title");
                    chapter.language = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("translatedLanguage");
                    chapter.hash = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("hash");
                    chapter.id = jsonChapterElem.getJSONObject("data").getString("id");

                    // Get each manga page url in this chapter
//                    JSONArray pagesURL = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getJSONArray("data");
//                    for (int j = 0; j < pagesURL.length(); j++) {
//                        chapter.images_url.add(pagesURL.getString(j));
//                    }

                    if (!chapter.volume.equals("null")) {
                        chapters.add(chapter);
                    }
                }

                // Loop queries to get all chapters
                while (countChapter + 1 < totalChapters) {
                    // The following query to get how many chapters there are for this manga
                    int offset = countChapter + 1;
                    query = baseURL + getChapters.replace("{id}", manga_id).replace("{offset}", String.valueOf(offset));
                    url = new URL(query);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    inputStream = conn.getInputStream();

                    // Convert input stream to String
                    scanner = new Scanner(inputStream).useDelimiter("\\A");
                    result = scanner.hasNext() ? scanner.next() : "";
                    Log.v("DEBUG_TAG", result);
                    Log.v("DEBUG_TAG", "i = " + countChapter + " total = " + totalChapters + " size = " + chapters.size());
                    // Parse JSON
                    jsonObject = new JSONObject(result);

                    results = jsonObject.getJSONArray("results");
                    for (int j = 0; j < results.length(); j++, countChapter++) {
                        Chapter chapter = new Chapter();

                        JSONObject jsonChapterElem = results.getJSONObject(j);
                        chapter.volume = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("volume");
                        chapter.chapter = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("chapter");
                        if (chapter.chapter.equals("null"))
                            chapter.chapter = "0";
                        chapter.title = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("title");
                        chapter.language = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("translatedLanguage");
                        chapter.hash = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("hash");
                        chapter.id = jsonChapterElem.getJSONObject("data").getString("id");

//                        // Get each manga page url in this chapter
//                        JSONArray pagesURL = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getJSONArray("data");
//                        for (int k = 0; k < pagesURL.length(); k++) {
//                            chapter.images_url.add(pagesURL.getString(k));
//                        }

                        if (!chapter.volume.equals("null")) {
                            chapters.add(chapter);
                        }
                    }
                }

                Collections.sort(chapters); // sort the chapters
                for(Chapter c : chapters) {
                    chapters_info.add("vol." + c.volume + " chapter." + c.chapter + ": " + c.title);
                    chapters_id.add(c.id);
                    chapters_hash.add(c.hash);
                }

                // Set the display
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView_chapters);
                rvAdapter = new RecyclerViewAdapterChapters(ct, chapters_info, chapters_id);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(rvAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ct));
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getData(){
        if (getIntent().hasExtra("id")) {
            manga_id = getIntent().getStringExtra("id");
            manga_description = getIntent().getStringExtra("description");
            title = getIntent().getStringExtra("title");
            tags = getIntent().getStringExtra("tags");
            textView_title.setText(title);
            textView_description.setMaxLines(4);
            textView_description.setText(manga_description);
            textView_description.setMovementMethod(new ScrollingMovementMethod());
            textView_genre.setMaxLines(1);
            textView_genre.setText(tags);
            textView_genre.setMovementMethod(new ScrollingMovementMethod());

            //testData.setText(id);
        }
    }
}