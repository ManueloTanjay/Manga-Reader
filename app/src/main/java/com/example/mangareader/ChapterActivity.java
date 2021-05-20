package com.example.mangareader;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ChapterActivity extends FragmentActivity {

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title=%s&limit=100&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga={id}&limit=100&offset={offset}&translatedLanguage=en";
    public String getBaseURL = "/at-home/server/%s";

    // Passing Data
    Bundle bundle;

    // Data
    public String naruto_id = "6b1eb93e-473a-4ab3-9922-1a66d2a29a4a";
    public ArrayList<Chapter> chapters = new ArrayList<>();

    private Thread getMangaThread;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        getMangaThread = new Thread(new getMangaRunnable());
        getMangaThread.start();

        bundle = new Bundle();

    }

    private class getMangaRunnable implements Runnable {
        @Override
        public void run() {
            try {
                // The first query to get how many chapters there are for this manga
                String query = baseURL + getChapters.replace("{id}", naruto_id).replace("{offset}", "0");
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
                int i = 0;
                Log.v("DEBUG_TAG", "i = " + i + " total = " + totalChapters + " size = " + chapters.size());

                JSONArray results = jsonObject.getJSONArray("results");
                for (i = 0; i < results.length(); i++) {
                    Chapter chapter = new Chapter();

                    JSONObject jsonChapterElem = results.getJSONObject(i);
                    chapter.chapter = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("chapter");
                    chapter.volume = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("volume");
                    chapter.title = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("title");
                    chapter.language = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("translatedLanguage");
                    chapter.hash = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("hash");
                    chapter.id = jsonChapterElem.getJSONObject("data").getString("id");

                    // Get each manga page url in this chapter
                    JSONArray pagesURL = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getJSONArray("data");
                    for (int j = 0; j < pagesURL.length(); j++) {
                        chapter.images_url.add(pagesURL.getString(j));
                    }

                    if (!chapter.volume.equals("null"))
                        chapters.add(chapter);
                }

                // Loop queries to get all chapters
                while (i + 1 < totalChapters) {
                    // The following query to get how many chapters there are for this manga
                    int offset = i + 1;
                    query = baseURL + getChapters.replace("{id}", naruto_id).replace("{offset}", String.valueOf(offset));
                    url = new URL(query);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    inputStream = conn.getInputStream();

                    // Convert input stream to String
                    scanner = new Scanner(inputStream).useDelimiter("\\A");
                    result = scanner.hasNext() ? scanner.next() : "";
                    Log.v("DEBUG_TAG", result);
                    Log.v("DEBUG_TAG", "i = " + i + " total = " + totalChapters + " size = " + chapters.size());
                    // Parse JSON
                    jsonObject = new JSONObject(result);

                    results = jsonObject.getJSONArray("results");
                    for (int j = 0; j < results.length(); j++, i++) {
                        Chapter chapter = new Chapter();

                        JSONObject jsonChapterElem = results.getJSONObject(j);
                        chapter.chapter = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("chapter");
                        chapter.volume = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("volume");
                        chapter.title = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("title");
                        chapter.language = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("translatedLanguage");
                        chapter.hash = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getString("hash");
                        chapter.id = jsonChapterElem.getJSONObject("data").getString("id");

                        // Get each manga page url in this chapter
                        JSONArray pagesURL = jsonChapterElem.getJSONObject("data").getJSONObject("attributes").getJSONArray("data");
                        for (int k = 0; k < pagesURL.length(); k++) {
                            chapter.images_url.add(pagesURL.getString(k));
                        }

                        if (!chapter.volume.equals("null"))
                            chapters.add(chapter);
                    }
                }
            System.out.println("");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}