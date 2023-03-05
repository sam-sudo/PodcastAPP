package com.example.pruebatecnicamerlin.ui.podcasts.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PodcastGenderAdapter extends ListAdapter<String,PodcastGenderAdapter.PodcastGenderViewHolder> {


    public PodcastGenderAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PodcastGenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                return new PodcastGenderViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.podcast_gender_item, viewGroup, false));
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PodcastGenderViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String item = getItem(position);
        viewHolder.bind(item);
    }



    public static class PodcastGenderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGender;

        public PodcastGenderViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvGender = (TextView) view.findViewById(R.id.tv_Gender);
        }



        public void bind(String string){
            tvGender.setText(string);



        }
    }






}


