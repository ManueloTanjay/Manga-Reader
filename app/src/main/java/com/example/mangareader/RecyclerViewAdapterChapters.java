package com.example.mangareader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterChapters extends RecyclerView.Adapter<RecyclerViewAdapterChapters.MyViewHolder> {

    Context ct;
    String chapters[];

    public RecyclerViewAdapterChapters(Context ct, String chapters[]) {
        this.ct = ct;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ct);
        View view = inflater.inflate(R.layout.manga_chapter_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView_item.setText(chapters[position]);
    }

    @Override
    public int getItemCount() {
        return chapters.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_item = itemView.findViewById(R.id.textView_item);
        }
    }
}
