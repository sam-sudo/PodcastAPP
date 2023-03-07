package com.example.pruebatecnicamerlin.ui.podcastDetail.adapter;

import android.media.AudioManager;
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
import com.example.pruebatecnicamerlin.util.MediaPlayerService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PodcastDetailAdapter extends ListAdapter<PodcastDetailResponse, PodcastDetailAdapter.PodcastViewHolder>  {
    private int currentPosition = -1;
    private MediaPlayer  mp = new MediaPlayerService().getMp();;
    private int lastPosition = -1;
    private boolean isTrackPaused = false;

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

        holder.btnPlayTrack.setTag(1);

        if(currentPosition == position ){
            holder.btnPlayTrack.setImageResource(R.drawable.ic_pause);
        } else {
            holder.btnPlayTrack.setImageResource(R.drawable.play);
        }





    }


    class PodcastViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvTitle;
        private TextView releaseDate;
        private TextView tvTimeDuration;
        private ImageButton btnPlayTrack;



        public PodcastViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitle = (TextView) view.findViewById(R.id.txt_title);
            releaseDate = (TextView) view.findViewById(R.id.tv_releaseDate);
            tvTimeDuration = (TextView) view.findViewById(R.id.tvTimeDuration);
            btnPlayTrack = view.findViewById(R.id.btn_playTrack);




        }

        public void startPauseTrack(String url, int position, ImageButton btnPlayTrack) {

            if(url != null){

                Uri uri = Uri.parse(url);


                if(mp == null){
                    mp = new MediaPlayer();

                }


                if(!mp.isPlaying()){

                /*Picasso.get()
                        .load(R.drawable.ic_pause)
                        .into(imageButton);*/

                    Log.d("TAG", "onClick: start mp");


                    if(lastPosition != position && lastPosition != -1 || !isTrackPaused){
                        mp.release();
                        mp = MediaPlayer.create(btnPlayTrack.getContext(), uri);
                        mp.start();
                        isTrackPaused = false;

                    }else {
                        mp.start();
                    }


                }else {
                    Log.d("TAG", "onClick: pause mp");
                /*Picasso.get()
                        .load(R.drawable.play)
                        .into(imageButton);*/

                    if(lastPosition != position && lastPosition != -1){
                        mp.release();
                        mp = MediaPlayer.create(btnPlayTrack.getContext(), uri);
                        mp.start();
                        isTrackPaused = false;
                    }else {
                        mp.pause();
                        isTrackPaused = true;
                    }




                }

                lastPosition = position;

            }else {
                Toast.makeText(btnPlayTrack.getContext(), "Ups! No track available...", Toast.LENGTH_SHORT).show();
            }



        }


        public void bind(PodcastDetailResponse podcastDetailResponse) {
            tvTitle.setText(podcastDetailResponse.getTrackName());
            releaseDate.setText(podcastDetailResponse.getReleaseDate().split("T")[0]);


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
                   Log.d("TAG", "onClick: " + podcastDetailResponse.getEpisodeUrl());
                   Log.d("TAG", "trackname: " + podcastDetailResponse.getTrackName());




                   int PlayStopButtonState = (int) btnPlayTrack.getTag();

                   int previousPosition = currentPosition;

                       if (PlayStopButtonState == 1) {
                           currentPosition = getBindingAdapterPosition();
                           btnPlayTrack.setImageResource(R.drawable.ic_pause);
                           btnPlayTrack.setTag(2);
                       } else {
                           currentPosition = -1;
                           btnPlayTrack.setImageResource(R.drawable.play);
                           btnPlayTrack.setTag(1);
                       }
                       if (previousPosition != -1) {
                           notifyItemChanged(previousPosition);
                       }


                    startPauseTrack(podcastDetailResponse.getEpisodeUrl(), getBindingAdapterPosition(),btnPlayTrack);
                   //startPauseTrack(podcastDetailResponse.getEpisodeUrl(), getBindingAdapterPosition(),mp);
               }
           });



           

        }
    }

}
