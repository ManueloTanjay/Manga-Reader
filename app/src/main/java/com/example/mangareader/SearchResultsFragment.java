package com.example.mangareader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultsFragment extends Fragment {

    RecyclerView recyclerView;

    String titles[], desc[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_results, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.results);

        titles = getResources().getStringArray(R.array.titles);
        desc = getResources().getStringArray(R.array.descriptions);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), titles, desc);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
