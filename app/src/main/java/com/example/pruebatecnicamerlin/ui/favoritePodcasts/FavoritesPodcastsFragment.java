package com.example.pruebatecnicamerlin.ui.favoritePodcasts;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.databinding.FragmentFavoritesPodcastsBinding;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.ui.favoritePodcasts.interfaces.FavoritePodcastInterface;
import com.example.pruebatecnicamerlin.ui.podcasts.adapters.PodcastAdapter;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesPodcastsFragment extends Fragment implements PodcastInterface {

    private FragmentFavoritesPodcastsBinding binding;

    private PodcastAdapter mPodcastAdapter;

    private FavoritePodcastViewModel favoritePodcastViewModel;

    private String imgTrack ;

    private SharedPreferences sharedPreferences;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("bug_fav", "onCreateView: ");

        binding = FragmentFavoritesPodcastsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.favoritePodcastViewModel = new ViewModelProvider(this).get(FavoritePodcastViewModel.class);

        //RecyclerView podcastList
        RecyclerView mRecyclerView = binding.recyclerView;

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        mPodcastAdapter = new PodcastAdapter(new DiffUtil.ItemCallback<PodcastResponse>() {


            @Override
            public boolean areItemsTheSame(@NonNull PodcastResponse oldItem, @NonNull PodcastResponse newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull PodcastResponse oldItem, @NonNull PodcastResponse newItem) {
                return false;
            }
        }, this);

        this.favoritePodcastViewModel.getFavoriteList(this.getContext()).observe(this, new Observer<ArrayList<PodcastResponse>>() {

            @Override
            public void onChanged(ArrayList<PodcastResponse> arrayList) {


                mPodcastAdapter.submitList(arrayList);

                Log.d("TAG_navigatio", "onChanged: viiewmodel");

            }
        });



        mRecyclerView.setAdapter(mPodcastAdapter);


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG_navigate", "onCreate: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG_navigate", "onResume: ");
        favoritePodcastViewModel.refreshFavoriteArrayList();
    }

    @Override
    public void onClick(int position) {

        navigateToDetail(position);
    }

    @Override
    public void onItemClick(int position) {
        navigateToDetail(position);
    }


    private void navigateToDetail(int position) {
        String id = favoritePodcastViewModel.getFavoriteRealList().get(position).getId().getAttributes().getId();
        String name = favoritePodcastViewModel.getFavoriteRealList().get(position).getName().getLabel();
        String author = favoritePodcastViewModel.getFavoriteRealList().get(position).getArtist().getLabel();
        imgTrack = favoritePodcastViewModel.getFavoriteRealList().get(position).getImage().get(2).getLabel();
        //todo open datail
        Log.d("TAG_navigate", "onClick:   ---> " + id);
        Log.d("TAG_navigate", "onClick: position  ---> " + position);

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_content_main);


        Bundle bundle = new Bundle();
        bundle.putString("podcastId", id);
        bundle.putString("imgTrack", imgTrack);
        bundle.putString("name", name);
        bundle.putString("author", author);
        navController.navigate(R.id.nav_podcast_detail,bundle);
    }



}