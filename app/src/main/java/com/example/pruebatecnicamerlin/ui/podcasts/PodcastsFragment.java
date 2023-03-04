package com.example.pruebatecnicamerlin.ui.podcasts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.databinding.FragmentPodcastsBinding;
import com.example.pruebatecnicamerlin.ui.podcasts.adapters.PodcastAdapter;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;

import java.util.ArrayList;

public class PodcastsFragment extends Fragment implements PodcastInterface {

    private FragmentPodcastsBinding binding;

    private PodcastAdapter mPodcastAdapter;

    private PodcastViewModel podcastViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPodcastsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView mRecyclerView = binding.recyclerView;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        mPodcastAdapter = new PodcastAdapter(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }
        }, this);
        mRecyclerView.setAdapter(mPodcastAdapter);

        podcastViewModel = new ViewModelProvider(this).get(PodcastViewModel.class);

        podcastViewModel.getList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList arrayList) {
                Log.d("TAG", "onChanged: ");
                mPodcastAdapter.submitList(arrayList);
            }
        });



        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(int position) {
        Log.d("TAG", "onClick:   ---> " + position);

    }
}