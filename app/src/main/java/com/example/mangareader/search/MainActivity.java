package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends FragmentActivity {

    SearchView searchView;

    public String baseURL = "https://api.mangadex.org";
    public String searchManga = "/manga?title=%s&limit=100&contentRating%5B%5D=safe";
    public String getChapters = "/chapter?manga=%s&limit=100&offset=%s&translatedLanguage=en";
    public String getBaseURL = "/at-home/server/%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchView);

        SearchView.OnQueryTextListener queryTextListener
                = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                FragmentManager fm = getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }

                SearchResultsFragment results = new SearchResultsFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.homepage, results);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
    }


}