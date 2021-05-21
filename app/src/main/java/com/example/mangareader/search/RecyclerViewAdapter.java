package com.example.mangareader.search;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context ct;
    String titles[], desc[], ids[];

    public RecyclerViewAdapter(Context ct, String titles[], String desc[], String ids[]) {
        this.ct = ct;
        this.titles = titles;
        this.desc = desc;
        this.ids = ids;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ct);
        View view = inflater.inflate(R.layout.search_result_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.t.setText(titles[position]);
        holder.d.setText(desc[position]);

        holder.pageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, PageActivity.class);
                intent.putExtra("id", ids[position]);
                ct.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView t, d;
        ConstraintLayout pageLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.search_result_title);
            d = itemView.findViewById(R.id.search_result_description);
            pageLayout = itemView.findViewById(R.id.row_index_key);
        }
    }
}
