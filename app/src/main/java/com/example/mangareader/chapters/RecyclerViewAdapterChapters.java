package com.example.mangareader.chapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangareader.R;
import com.example.mangareader.pages.PageActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapterChapters extends RecyclerView.Adapter<RecyclerViewAdapterChapters.MyViewHolder> {

    Context ct;
    //    String chapters[];
    ArrayList<String> chapters;
    ArrayList<String> chapters_hash;

    //    public RecyclerViewAdapterChapters(Context ct, String chapters[]) {
    public RecyclerViewAdapterChapters(Context ct, ArrayList<String> chapters, ArrayList<String> chapters_hash) {
        this.ct = ct;
        this.chapters = chapters;
        this.chapters_hash = chapters_hash;
        System.out.println("Rec");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ct);
        View view = inflater.inflate(R.layout.chapter_manga_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView_item.setText(chapters.get(position));

        holder.chapter_index_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, PageActivity.class);
                intent.putExtra("chapter_hash", chapters_hash.get(position));
                ct.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_item;
        ConstraintLayout chapter_index_key;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_item = itemView.findViewById(R.id.textView_item);
            chapter_index_key = itemView.findViewById(R.id.chapter_index_key);
        }
    }
}
