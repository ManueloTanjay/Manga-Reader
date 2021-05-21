package com.example.mangareader.search;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import androidx.appcompat.widget.SearchView;

import com.example.mangareader.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends FragmentActivity {

    public SearchView searchView;
    public String query;
    public ArrayList<Manga> mangas;
    public ArrayList<String> titles;
    public ArrayList<String> descriptions;
    public ArrayList<String> ids;

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title={title}&limit=100&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga=%s&limit=100&offset=%s&translatedLanguage=en";
    public String getBaseURL = "/at-home/server/%s";

    public String url;
    public InputStream stringResult;

    SearchResultsFragment results;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchView);

        SearchView.OnQueryTextListener queryTextListener
                = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                clearFragmentBStack();
                searchView.clearFocus();

                query = searchView.getQuery().toString();
                loadSearchResults(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
    }

    public void loadSearchResults(String query){
        int offset = 0;
        url = baseURL + searchManga.replace("{title}", query);

        SearchThread search = new SearchThread();
        search.start();
    }

    public void clearFragmentBStack(){
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    class SearchThread extends Thread {

        @Override
        public void run() {

            try {

                URL u = new URL(url);
                HttpsURLConnection request = (HttpsURLConnection) u.openConnection();
                request.connect();
                System.out.println(url);

                stringResult = request.getInputStream();

                Scanner s = new Scanner(stringResult).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                System.out.println(result);

                JSONObject jsonResult = new JSONObject(result);
                System.out.println("json loaded");

                JSONArray searchResults = jsonResult.getJSONArray("results");
                System.out.println("jsonarray loaded");

                mangas = new ArrayList<>();
                titles = new ArrayList<>();
                descriptions = new ArrayList<>();
                ids = new ArrayList<>();
                for (int i = 0; i < searchResults.length(); i++) {
                    JSONObject entry = searchResults.getJSONObject(i);
                    String title = entry.getJSONObject("data").getJSONObject("attributes").getJSONObject("title").getString("en");
                    String id = entry.getJSONObject("data").getString("id");
                    String description = entry.getJSONObject("data").getJSONObject("attributes").getJSONObject("description").getString("en");
                    Manga insert = new Manga(title, id, description);
                    mangas.add(insert);
                    titles.add(title);
                    descriptions.add(description);
                    ids.add(id);
                }
                System.out.println("mangalist loaded");

                loadFragment();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void loadFragment(){

            bundle = new Bundle();
            bundle.putStringArrayList("mangaTitles", titles);
            bundle.putStringArrayList("mangaDescriptions", descriptions);
            bundle.putStringArrayList("mangaIds", ids);

            results = new SearchResultsFragment();
            results.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.homepage, results);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

}