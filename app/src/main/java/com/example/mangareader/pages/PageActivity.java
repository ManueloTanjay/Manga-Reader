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
    ArrayList<String> imgsUrl = new ArrayList<String>() {
        {
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/21-f7e03fca6ea8676446ee3a0267205fb8fcce272d37415fffebc34ccb4163183d.png");
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/22-10c1e42b46ef33a2ce2229a02c17715b11e75f3af823ddbc915b3a9dc272a00f.png");
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/23-3bf0cecdc6241b1b50cf64415c43af9cfa3f6c07b1356615efdb428b065ba1ba.png");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        ViewPager viewPager = findViewById(R.id.viewPager);

        PageMangaAdapter adapter = new PageMangaAdapter(this);
        viewPager.setAdapter(adapter);

//        testData = findViewById(R.id.testInfo);
//        getData();
    }

    private void getData(){
        if (getIntent().hasExtra("chapter_id")) {
            id = getIntent().getStringExtra("chapter_id");
            testData.setText(id);
        }
    }
}