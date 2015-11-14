package com.example.adrian.findme.places.most.popular;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrian.findme.R;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Adrian on 06-Sep-15.
 */
public class MostPopularFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this.getActivity(), "Area");
        adapter.setTextKey("name");
        adapter.setImageKey("logo");

        final ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //Get category item position to pass to PlacesActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
