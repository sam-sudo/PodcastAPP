package com.example.pruebatecnicamerlin.ui.podcasts.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;

import java.util.ArrayList;

public class PodcastAdapter  extends ListAdapter<String,PodcastAdapter.PodcastViewHolder> {
    PodcastInterface podcastInterface;

    public PodcastAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback, PodcastInterface podcastInterface) {
        super(diffCallback);
        this.podcastInterface = podcastInterface;
    }

    @NonNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PodcastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: " + getItem(position));
        String item = getItem(position);
        holder.bind(item);
    }


    class PodcastViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Button btn;

        public PodcastViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            Log.d("TAG", "PodcastViewHolder: --");
            textView = (TextView) view.findViewById(R.id.txt_podcastItem);
            btn = (Button) view.findViewById(R.id.btn_img);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    podcastInterface.onClick(getAdapterPosition());
                }
            });

        }

        public TextView getTextView() {
            return textView;
        }

        public void bind(String string){
            textView.setText(string);
        }

    }


}



