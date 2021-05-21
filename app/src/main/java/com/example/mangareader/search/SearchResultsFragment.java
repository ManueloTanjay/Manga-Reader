package com.example.mangareader.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangareader.R;
import com.example.mangareader.search.RecyclerViewAdapter;

import java.util.List;

public class SearchResultsFragment extends Fragment {

    RecyclerView recyclerView;
    MainActivity act;

    String[] titles, descs, ids;
    int size;
//    List<String> titles;
//    List<String> descs;
//    List<String> ids;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_results, container, false);

        act = (MainActivity) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.results);

        size = getArguments().getStringArrayList("mangaTitles").size();
        titles = new String[size];
        descs = new String[size];
        ids = new String[size];
        for(int i = 0; i < size; i++) {
            titles[i] = getArguments().getStringArrayList("mangaTitles").get(i);
            descs[i] = getArguments().getStringArrayList("mangaDescriptions").get(i);
            ids[i] = getArguments().getStringArrayList("mangaIds").get(i);

            if(descs[i].length() > 100)
                descs[i] = descs[i].substring(0, 100) + "...";
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), titles, descs, ids);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
