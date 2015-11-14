package com.example.adrian.findme.places.featured;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrian.findme.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adrian on 12-Sep-15.
 */
public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {

    private ParseQueryAdapter<ParseObject> parseAdapter;

    private ViewGroup parseParent;

    private FeaturedAdapter featuredAdapter = this;

    public FeaturedAdapter(Context context, ViewGroup parentIn) {
        parseParent = parentIn;

        ParseQueryAdapter.QueryFactory<ParseObject> queryFactory = new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // Here we can configure a ParseQuery to our heart's desire.
                ParseQuery query = new ParseQuery("Place");
//                query.whereEqualTo("featured", true);
//                query.orderByDescending("featured_priority");

                return query;
            }
        };

        parseAdapter = new ParseQueryAdapter<ParseObject>(context, queryFactory) {

            @Override
            public View getItemView(ParseObject object, View view, ViewGroup parent) {
                if (view == null) {
                    view = View.inflate(getContext(), R.layout.item_featured, null);
                }

                ParseImageView imageView = (ParseImageView) view.findViewById(R.id.featuredHeaderImage);
                imageView.setParseFile(object.getParseFile("banner"));
                imageView.loadInBackground();

                TextView nameView = (TextView) view.findViewById(R.id.featuredName);
                nameView.setText(object.getString("name"));
                return view;
            }

            @Override
            public View getNextPageView(View v, ViewGroup parent) {
                if (v == null) {
                    v = View.inflate(getContext(), R.layout.adapter_next_page, null);
                }
                TextView textView = (TextView) v.findViewById(R.id.featuredName);
                textView.setText("Loaded " + getCount() + " rows. Get more!");
                return v;
            }
        };
        parseAdapter.addOnQueryLoadListener(new OnQueryLoadListener());
        parseAdapter.setPaginationEnabled(true);
        parseAdapter.loadObjects();
    }

    @Override
    public FeaturedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_featured, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        parseAdapter.getView(position, holder.itemView, parseParent);
    }

    @Override
    public int getItemCount() {
        return parseAdapter.getCount();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView FeaturedName;
        public final TextView FeaturedDescription;
        public final ImageView FeaturedHeaderImage;

        public ViewHolder(View v) {
            super(v);

            FeaturedName = (TextView) v.findViewById(R.id.featuredName);
            FeaturedDescription = (TextView) v.findViewById(R.id.featuredDescription);
            FeaturedHeaderImage = (ImageView) v.findViewById(R.id.featuredHeaderImage);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    public class OnQueryLoadListener implements ParseQueryAdapter.OnQueryLoadListener<ParseObject> {

        public void onLoading() {

        }

        public void onLoaded(List<ParseObject> objects, Exception e) {
            featuredAdapter.notifyDataSetChanged();
        }
    }
}
