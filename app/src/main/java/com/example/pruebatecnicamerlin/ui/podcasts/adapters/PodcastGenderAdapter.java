package com.example.pruebatecnicamerlin.ui.podcasts.adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastGenderInterface;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;
import com.example.pruebatecnicamerlin.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PodcastGenderAdapter extends ListAdapter<String,PodcastGenderAdapter.PodcastGenderViewHolder> {

    PodcastGenderInterface podcastGenderInterface;

    public PodcastGenderAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback,PodcastGenderInterface podcastGenderInterface) {
        super(diffCallback);
        this.podcastGenderInterface = podcastGenderInterface;
    }

    @Override
    public PodcastGenderViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                return new PodcastGenderViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.podcast_gender_item, viewGroup, false));
    }



    @Override
    public void onBindViewHolder(PodcastGenderViewHolder viewHolder, final int position) {
        String item = getItem(position);
        viewHolder.bind(item);
    }



    class PodcastGenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvGender;
        private LinearLayoutCompat ll_itemGender;

        public PodcastGenderViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvGender = (TextView) view.findViewById(R.id.tv_Gender);
            ll_itemGender =  view.findViewById(R.id.ll_itemGender);

            ll_itemGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    podcastGenderInterface.onGenderClick(getTvGender().getText().toString());
                }
            });

        }

        public TextView getTvGender() {
            return tvGender;
        }

        public void bind(String string){
            tvGender.setText(string);



        }

        @Override
        public void onClick(View view) {
        }
    }






}


