package com.example.mangareader.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mangareader.R;

public class PageActivity extends AppCompatActivity {

    TextView testData;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        testData = findViewById(R.id.testInfo);

        getData();
    }

    private void getData(){
        if (getIntent().hasExtra("chapter_id")) {
            id = getIntent().getStringExtra("chapter_id");
            testData.setText(id);
        }
    }
}