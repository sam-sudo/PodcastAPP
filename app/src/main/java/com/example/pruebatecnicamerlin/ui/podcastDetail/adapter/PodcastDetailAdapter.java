package com.example.pruebatecnicamerlin.ui.podcastDetail.adapter;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;
import com.example.pruebatecnicamerlin.ui.podcastDetail.interfaces.PodcastDetailInterface;
import com.example.pruebatecnicamerlin.util.CircleTransform;
import com.squareup.picasso.Picasso;

public class PodcastDetailAdapter extends ListAdapter<PodcastDetailResponse, PodcastDetailAdapter.PodcastViewHolder>  {

    PodcastDetailInterface podcastDetailInterface;
    public PodcastDetailAdapter(@NonNull DiffUtil.ItemCallback<PodcastDetailResponse> diffCallback, PodcastDetailInterface podcastDetailInterface) {
        super(diffCallback);
        this.podcastDetailInterface = podcastDetailInterface;
    }

    @NonNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PodcastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_detail_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastViewHolder holder, int position) {
        PodcastDetailResponse item = getItem(position);
        holder.bind(item);
    }


    class PodcastViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView releaseDate;
        private TextView tvTimeDuration;
        private ImageButton btnPlayTrack;



        public PodcastViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitle = (TextView) view.findViewById(R.id.txt_title);
            tvAuthor = (TextView) view.findViewById(R.id.txt_Author);
            releaseDate = (TextView) view.findViewById(R.id.tv_releaseDate);
            tvTimeDuration = (TextView) view.findViewById(R.id.tvTimeDuration);
            btnPlayTrack = view.findViewById(R.id.btn_playTrack);




        }


        public void bind(PodcastDetailResponse podcastDetailResponse) {
            tvTitle.setText(podcastDetailResponse.getTrackName());
            tvAuthor.setText(podcastDetailResponse.getArtistName());
            releaseDate.setText(podcastDetailResponse.getReleaseDate());



            try {
               long milliseconds = Integer.parseInt(podcastDetailResponse.getTrackTimeMillis());

               long minutes =  ((milliseconds / 1000) /  60);
               long hours = (minutes / 60) ;
               long seconds =  ((milliseconds / 1000) %  60);

               String realHours = String.format("%02d", hours);
               String realMinutes = String.format("%02d", minutes);
               String realSeconds = String.format("%02d", seconds);

               if (minutes > 60){

                   realHours = String.format("%02d", minutes / (60 * hours));

                   tvTimeDuration.setText(realHours + ":" + realHours + ":" + seconds);
               }else{
                   tvTimeDuration.setText(realMinutes + ":" + realSeconds);
               }
           }catch (Exception e){

           }


           btnPlayTrack.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   podcastDetailInterface.startPauseTrack(podcastDetailResponse.getEpisodeUrl(), btnPlayTrack);
               }
           });
           

        }
    }
}
