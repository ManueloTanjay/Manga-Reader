package com.example.mangareader.chapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangareader.R;

public class MangaChaptersFragment extends Fragment {

    RecyclerView recyclerView;
    String[] chapters_info, chapters_id;
    int size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chapters_fragment_manga_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_chapters);

//        chapters_info = getResources().getStringArray(R.array.chapters);
        size = getArguments().getStringArrayList("chapters_info").size();
        chapters_info = new String[size];
        chapters_id = new String[size];

        for (int i = 0; i < size; i++) {
            chapters_info[i] = getArguments().getStringArrayList("chapters_info").get(i);
            chapters_id[i] = getArguments().getStringArrayList("chapters_id").get(i);
        }

        RecyclerViewAdapterChapters recyclerViewAdapterChapters = new RecyclerViewAdapterChapters(getContext(), chapters_info);
        recyclerView.setAdapter(recyclerViewAdapterChapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
