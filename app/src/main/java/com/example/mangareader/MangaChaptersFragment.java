package com.example.mangareader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MangaChaptersFragment extends Fragment {

    RecyclerView recyclerView;
    String chapters[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_chapters_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_chapters);

        chapters = getResources().getStringArray(R.array.chapters);

        RecyclerViewAdapterChapters recyclerViewAdapterChapters = new RecyclerViewAdapterChapters(getContext(), chapters);
        recyclerView.setAdapter(recyclerViewAdapterChapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
