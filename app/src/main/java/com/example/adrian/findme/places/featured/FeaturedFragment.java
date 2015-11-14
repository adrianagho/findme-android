package com.example.adrian.findme.places.featured;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrian.findme.R;
import com.example.adrian.findme.models.PlaceCategory;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Adrian on 06-Sep-15.
 */
public class FeaturedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        FeaturedAdapter featuredAdapter = new FeaturedAdapter(view.getContext(), container);

        mRecyclerView.setAdapter(featuredAdapter);

        return view;
    }
}
