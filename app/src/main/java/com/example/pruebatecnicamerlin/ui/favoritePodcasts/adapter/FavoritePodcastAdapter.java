package com.example.pruebatecnicamerlin.ui.favoritePodcasts.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;
import com.example.pruebatecnicamerlin.util.CircleTransform;
import com.squareup.picasso.Picasso;

public class FavoritePodcastAdapter extends ListAdapter<PodcastResponse, FavoritePodcastAdapter.FavoritePodcastViewModel> {

    PodcastInterface podcastInterface;

    protected FavoritePodcastAdapter(@NonNull DiffUtil.ItemCallback<PodcastResponse> diffCallback, PodcastInterface podcastInterface) {
        super(diffCallback);
        this.podcastInterface = podcastInterface;
    }

    @NonNull
    @Override
    public FavoritePodcastViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoritePodcastViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePodcastViewModel holder, int position) {
        PodcastResponse item = getItem(position);
        holder.bind(item);
    }


    class FavoritePodcastViewModel extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvDate;
        private TextView tvCapsCount;
        private ImageButton imageTrack;

        public FavoritePodcastViewModel(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitle = (TextView) view.findViewById(R.id.txt_title);
            tvAuthor = (TextView) view.findViewById(R.id.txt_Author);
            tvDate = (TextView) view.findViewById(R.id.txt_date);
            tvCapsCount = (TextView) view.findViewById(R.id.tvCapsCount);

            imageTrack = (ImageButton) view.findViewById(R.id.imb_track);

            view.setOnClickListener(this);
            imageTrack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    podcastInterface.onClick(getBindingAdapterPosition());
                    Log.d("TAG", "img track onClick: -> " +getBindingAdapterPosition());
                }
            });

        }


        public void bind(PodcastResponse podcastResponse){
            tvTitle.setText(podcastResponse.getName().getLabel());
            tvAuthor.setText(podcastResponse.getArtist().getLabel());
            tvDate.setText(podcastResponse.getReleaseDate().getAttributes().getLabel());
            //tvCapsCount.setText(podcastResponse.getReleaseDate().getAttributes().getLabel());

            String urlImg = podcastResponse.getImage().get(2).getLabel();
            if(urlImg == null){
                urlImg = "https://podcastindex.org/api/images/podserve.png";
                Picasso.get()
                        .load(urlImg)
                        .resize(100,100)
                        .into(imageTrack);

                return;
            }

            Picasso.get().load(urlImg).transform(new CircleTransform()).into(imageTrack);
        }


        @Override
        public void onClick(View view) {
            podcastInterface.onItemClick(getBindingAdapterPosition());

        }
    }

}
