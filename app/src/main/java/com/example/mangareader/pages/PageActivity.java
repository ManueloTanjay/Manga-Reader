package com.example.mangareader.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mangareader.R;

import java.util.ArrayList;

public class PageActivity extends AppCompatActivity {

    TextView testData;
    String id;
    ArrayList<Bitmap> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        ViewPager viewPager = findViewById(R.id.viewPager);

        PageMangaAdapter adapter = new PageMangaAdapter(this);
        viewPager.setAdapter(adapter);
//        testData = findViewById(R.id.testInfo);
//
//        getData();
    }

    private void getData(){
        if (getIntent().hasExtra("chapter_hash")) {
            id = getIntent().getStringExtra("chapter_hash");
            testData.setText(id);
        }
    }
}