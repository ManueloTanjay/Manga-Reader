package com.example.mangareader.pages;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mangareader.R;

public class PageMangaAdapter extends PagerAdapter {

    private Context ctx;
    private int[] imageArray = new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3};

    public PageMangaAdapter(Context context) {
        ctx = context;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imgView = new ImageView(ctx);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP); // center the imgView
        imgView.setImageResource(imageArray[position]); // set imgView to hold the image
        container.addView(imgView, 0); // set container to hold imgView
        return imgView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
