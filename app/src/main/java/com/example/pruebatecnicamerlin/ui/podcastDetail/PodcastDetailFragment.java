package com.example.pruebatecnicamerlin.ui.podcastDetail;

import static android.content.Context.MODE_PRIVATE;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PodcastDetailFragment extends Fragment implements PodcastDetailInterface {

    private PodcastDetailViewModel mViewModel;
    private FragmentPodcastDetailBinding binding;

    private PodcastDetailAdapter podcastDetailAdapter;


    private String trackCount;

    private boolean isPodcastFavorite;

    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPodcastDetailBinding.inflate(inflater,container,false);
        mViewModel = new ViewModelProvider(this).get(PodcastDetailViewModel.class);

        String podcastId = getArguments().getString("podcastId");
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
            Picasso.get()
                    .load(imgTrack)
                    .transform(new CircleTransform())
                    .into(binding.imgTrack);

        }

        //load data from sharedPreferences
        loadData(podcastId);

        binding.ivFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if( isPodcastFavorite  ){
                    Picasso.get()
                            .load(R.drawable.ic_heart_incomplete)
                            .into(binding.ivFavorite);
                    isPodcastFavorite = false;
                    removeData(podcastId);

                }else {
                    Picasso.get()
                            .load(R.drawable.ic_heart_complete)
                            .into(binding.ivFavorite);
                    isPodcastFavorite = true;
                    saveData(podcastId);
                }
            }
        });


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


        mViewModel.getDetailList(podcastId).observe(this, new Observer<ArrayList<PodcastDetailResponse>>() {
            @Override
            public void onChanged(ArrayList<PodcastDetailResponse> arrayList) {
                Log.d("TAG", "onChanged: ");
                podcastDetailAdapter.submitList(arrayList);
                trackCount = arrayList.get(0).getTrackCount();
                binding.tvEpisodes.setText(trackCount + " Episodes");

            }
        });


        recyclerView.setAdapter(podcastDetailAdapter);
        return binding.getRoot();
    }


    @Override
    public void getCountTracks(int countTracks) {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Para que no se siga reproduciendo cuando vamos atras
        //todo si queremos seguir reproduciendod espues de cerrar la vista quitar este código
        /*if(mp != null){
            mp.release();
        }*/
    }


    private void loadData(String id) {
        Log.d("TAG", "loaData: ");

        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        sharedPreferences = this.getContext().getSharedPreferences("favorites", MODE_PRIVATE);

        String existId = sharedPreferences.getString(""+id, null);
        Log.d("TAG", "loadData: shared id --> " + existId);

        if( existId == null  ){
            Picasso.get()
                    .load(R.drawable.ic_heart_incomplete)
                    .into(binding.ivFavorite);
            isPodcastFavorite = false;

        }else {
            Picasso.get()
                    .load(R.drawable.ic_heart_complete)
                    .into(binding.ivFavorite);
            isPodcastFavorite = true;
        }
    }

    private void saveData(String id) {
        Log.d("TAG", "saveData: ");
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        sharedPreferences = this.getContext().getSharedPreferences("favorites", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString(""+id, id);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

    }

    private void removeData(String id) {
        Log.d("TAG", "saveData: ");
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        sharedPreferences = this.getContext().getSharedPreferences("favorites", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // below line is to save data in shared
        // prefs in the form of string.
        editor.remove(""+id);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

    }

}