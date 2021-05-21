package com.example.mangareader.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import java.util.Scanner;

public class PageActivity extends AppCompatActivity {

    TextView testData;
    String chapter_id;
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> imgsUrl = new ArrayList<String>();
    String hash;
//    {
//        {
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/21-f7e03fca6ea8676446ee3a0267205fb8fcce272d37415fffebc34ccb4163183d.png");
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/22-10c1e42b46ef33a2ce2229a02c17715b11e75f3af823ddbc915b3a9dc272a00f.png");
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/23-3bf0cecdc6241b1b50cf64415c43af9cfa3f6c07b1356615efdb428b065ba1ba.png");
//        }
//    };
    private String chapterInfoquery = "https://api.mangadex.org/chapter/{chapter_id}";

    public String baseURL = "https://api.mangadex.org";
    public String getBaseURL = "/at-home/server/{chapter_id}";

    private Thread getChapterThread;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        // Query APi for info of this chapter
        getData();

        getChapterThread = new Thread(new GetChapterRunnable(this));
        getChapterThread.start();
    }

    private void getData(){
        if (getIntent().hasExtra("chapter_id")) {
            chapter_id = getIntent().getStringExtra("chapter_id");
//            testData.setText(chapter_id);
        }
    }

    private class GetChapterRunnable implements Runnable {

        Context ctx;

        public GetChapterRunnable(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                // Get the base URL which contain a token that expires in 15 minutes
                String baseURLquery =  baseURL + getBaseURL.replace("{chapter_id}", chapter_id);
                URL url = new URL(baseURLquery);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                inputStream = conn.getInputStream();

                // Convert input stream to String
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";
                Log.v("DEBUG_TAG", result);

                // Parse JSON
                JSONObject jsonObject = new JSONObject(result);
                String chapterBaseURL = jsonObject.getString("baseUrl");
                chapterBaseURL = chapterBaseURL.replace("\\", "");

                // Get JSON chapter info
                String query = chapterInfoquery.replace("{chapter_id}", chapter_id);
                url = new URL(query);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                inputStream = conn.getInputStream();

                // Convert input stream to String
                scanner = new Scanner(inputStream).useDelimiter("\\A");
                result = scanner.hasNext() ? scanner.next() : "";
                Log.v("DEBUG_TAG", result);

                // Parse JSON
                jsonObject = new JSONObject(result);
                hash = jsonObject.getJSONObject("data").getJSONObject("attributes").getString("hash");
                JSONArray jsonArrayImgUrl = jsonObject.getJSONObject("data").getJSONObject("attributes").getJSONArray("data");
                for (int i = 0; i < jsonArrayImgUrl.length(); i++) {
                    imgsUrl.add(jsonArrayImgUrl.getString(i));
                }

                // Create the final URL query
                for (int i = 0; i < imgsUrl.size(); i++) {
                    imgsUrl.set(i, chapterBaseURL + "/data/" + hash + "/" + imgsUrl.get(i));
                }

                // Set the page Adapter
                ViewPager viewPager = findViewById(R.id.viewPager);

                PageMangaAdapter adapter = new PageMangaAdapter(ctx, imgsUrl);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setAdapter(adapter);
                    }
                });

                // Parse JSON
            }  catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}