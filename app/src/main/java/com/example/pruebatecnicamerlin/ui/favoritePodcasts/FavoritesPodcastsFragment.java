package com.example.pruebatecnicamerlin.ui.favoritePodcasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pruebatecnicamerlin.databinding.FragmentFavoritesPodcastsBinding;

public class FavoritesPodcastsFragment extends Fragment {

    private FragmentFavoritesPodcastsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritePodcastViewModel galleryViewModel =
                new ViewModelProvider(this).get(FavoritePodcastViewModel.class);

        binding = FragmentFavoritesPodcastsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.txtListOfFavoritePodcasts;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}