package com.example.mangareader.pages;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mangareader.R;

import java.util.ArrayList;

public class PageMangaAdapter extends PagerAdapter {

    private Context ctx;
    private int[] imageArray = new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private ArrayList<String> imgURLArray = new ArrayList<String>() {
        {
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/21-f7e03fca6ea8676446ee3a0267205fb8fcce272d37415fffebc34ccb4163183d.png");
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/22-10c1e42b46ef33a2ce2229a02c17715b11e75f3af823ddbc915b3a9dc272a00f.png");
            add("https://ner5efrtww4pr.98s0casa9jrd0.mangadex.network:443/8SbpeQd2KxLt2hfMSFFRtCZXxnrpqZoenNekLzqOsW6SRMlYpTo-i9fgxcFXM13q0G-RPf5EgltkQ9l_h9-5n5nYce5NWN69XfB8XPiwxDhpHI_pbm-Hddn5C9GKNa9XTWFUVDgEgmMbaueU9Lg_PNNC8jgtHU6JBQAiaJS3ubLIbVHVRGIhsL2Lzk28Lzom/data/040ba5c0bc64fe338e0580a728b9f31e/23-3bf0cecdc6241b1b50cf64415c43af9cfa3f6c07b1356615efdb428b065ba1ba.png");
        }
    };

    public PageMangaAdapter(Context context) {
        ctx = context;
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
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP); // center the imgView
//        imgView.setImageResource(imageArray[position]); // set imgView to hold the image

        container.addView(imgView, 0); // set container to hold imgView
        return imgView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
