package com.example.pruebatecnicamerlin.ui.podcastDetail;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.databinding.FragmentPodcastDetailBinding;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;
import com.example.pruebatecnicamerlin.ui.podcastDetail.adapter.PodcastDetailAdapter;
import com.example.pruebatecnicamerlin.ui.podcastDetail.interfaces.PodcastDetailInterface;
import com.example.pruebatecnicamerlin.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PodcastDetailFragment extends Fragment implements PodcastDetailInterface {

    private PodcastDetailViewModel mViewModel;
    private FragmentPodcastDetailBinding binding;

    private PodcastDetailAdapter podcastDetailAdapter;

    private MediaPlayer mp;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPodcastDetailBinding.inflate(inflater,container,false);
        mViewModel = new ViewModelProvider(this).get(PodcastDetailViewModel.class);

        String args = getArguments().getString("podcastId");
        String imgTrack = getArguments().getString("imgTrack");
        String name = getArguments().getString("name");
        String author = getArguments().getString("author");

        //binding----------
        RecyclerView recyclerView = binding.recyclerViewDetail;

        binding.txtTitle.setText(name);
        binding.txtAuthor.setText(author);
        if(imgTrack == null){
            imgTrack = "https://podcastindex.org/api/images/podserve.png";
            Picasso.get()
                    .load(imgTrack)
                    .resize(100,100)
                    .into(binding.imgTrack);


        }else {
            Picasso.get().load(imgTrack).transform(new CircleTransform()).into(binding.imgTrack);

        }


        podcastDetailAdapter = new PodcastDetailAdapter(new DiffUtil.ItemCallback<PodcastDetailResponse>() {
            @Override
            public boolean areItemsTheSame(@NonNull PodcastDetailResponse oldItem, @NonNull PodcastDetailResponse newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull PodcastDetailResponse oldItem, @NonNull PodcastDetailResponse newItem) {
                return false;
            }
        },this);


        mViewModel.getDetailList(args).observe(this, new Observer<ArrayList<PodcastDetailResponse>>() {
            @Override
            public void onChanged(ArrayList<PodcastDetailResponse> arrayList) {
                Log.d("TAG", "onChanged: ");
                podcastDetailAdapter.submitList(arrayList);

            }
        });


        recyclerView.setAdapter(podcastDetailAdapter);
        return binding.getRoot();
    }


    @Override
    public void getCountTracks(int countTracks) {


    }

    @Override
    public void startPauseTrack(String url, ImageButton imageButton) {

        if(url != null){

            Uri uri = Uri.parse(url);

            if(mp == null){
                mp = MediaPlayer.create(this.getContext(), uri);
            }

            if(!mp.isPlaying()){

                Picasso.get()
                        .load(R.drawable.ic_pause)
                        .into(imageButton);

                Log.d("TAG", "onClick: start mp");
                mp.start();

            }else {
                Log.d("TAG", "onClick: pause mp");
                Picasso.get()
                        .load(R.drawable.play)
                        .into(imageButton);

                mp.pause();
            }

        }else {
            Toast.makeText(this.getContext(), "Ups! No track available...", Toast.LENGTH_SHORT).show();
        }
    }
}