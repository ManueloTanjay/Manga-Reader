package com.example.mangareader.pages;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mangareader.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PageMangaAdapter extends PagerAdapter {

    private Context ctx;
    private int[] imageArray = new int[] {R.drawable.img1};
    private ArrayList<String> imgURLArray = new ArrayList<String>();
//    {
//        {
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network/EQV_mOMcxFTPU8j9CsFUL7gn4K2Jr1eoTQUSI6RrGVMi3Kg4y8HcRikFBl9XY14M_TqPZc2IJ74ofOi-O-JNdfHHE6rey8iY2HGe1DVFsJzsLk_LLIEQq-Z8Q4zVorNbCvmh9kVYWdDVbqpUZHoCCprVyp-62n51YXee0gVqeiKBc4WY0sTbvMMdyynwIxzn/data/b89e88166ad42961f21f99f73b95435c/x10-5820dbc6a9d98502cd99d8b9ca16d0ecfc2136080fdbbc3cf7aab60ec4da2a4c.png");
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/EQV_mOMcxFTPU8j9CsFUL7gn4K2Jr1eoTQUSI6RrGVMi3Kg4y8HcRikFBl9XY14M_TqPZc2IJ74ofOi-O-JNdfHHE6rey8iY2HGe1DVFsJzsLk_LLIEQq-Z8Q4zVorNbCvmh9kVYWdDVbqpUZHoCCprVyp-62n51YXee0gVqeiKBc4WY0sTbvMMdyynwIxzn/data/b89e88166ad42961f21f99f73b95435c/x11-289810ff68447f8ec49532d5b2764d82dfaed6e56aacba153278896062cac213.png");
//            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/EQV_mOMcxFTPU8j9CsFUL7gn4K2Jr1eoTQUSI6RrGVMi3Kg4y8HcRikFBl9XY14M_TqPZc2IJ74ofOi-O-JNdfHHE6rey8iY2HGe1DVFsJzsLk_LLIEQq-Z8Q4zVorNbCvmh9kVYWdDVbqpUZHoCCprVyp-62n51YXee0gVqeiKBc4WY0sTbvMMdyynwIxzn/data/b89e88166ad42961f21f99f73b95435c/x12-b96b09bb66d6e3ae7be5628fc56f0cb30aa86f6b52c51c2ed2d1d6f89e7ec1ba.png");
//        }
//    };

    public PageMangaAdapter(Context context, ArrayList<String> imgURLArray) {
        ctx = context;
        this.imgURLArray = imgURLArray;
    }

    @Override
    public int getCount() {
        if (imgURLArray != null)
            return imgURLArray.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imgView = new ImageView(ctx);
        imgView.setScaleType(ImageView.ScaleType.FIT_CENTER); // center the imgView
//        imgView.setImageResource(imageArray[position]); // set imgView to hold the image

        Picasso.get().load(imgURLArray.get(position))
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_found).into(imgView);

        container.addView(imgView); // set container to hold imgView
        return imgView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
