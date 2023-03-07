package com.example.pruebatecnicamerlin.ui.podcasts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnicamerlin.R;
import com.example.pruebatecnicamerlin.databinding.FragmentPodcastsBinding;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.ui.podcastDetail.PodcastDetailFragment;
import com.example.pruebatecnicamerlin.ui.podcasts.adapters.PodcastAdapter;
import com.example.pruebatecnicamerlin.ui.podcasts.adapters.PodcastGenderAdapter;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastGenderInterface;
import com.example.pruebatecnicamerlin.ui.podcasts.interfaces.PodcastInterface;

import java.util.ArrayList;

public class PodcastsFragment extends Fragment implements PodcastInterface, PodcastGenderInterface {

    private FragmentPodcastsBinding binding;

    private PodcastAdapter mPodcastAdapter;

    private PodcastViewModel podcastViewModel;
    PodcastGenderAdapter podcastGenderAdapter;
    String imgTrack ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPodcastsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        podcastViewModel = new ViewModelProvider(this).get(PodcastViewModel.class);

        //RecyclerView podcastList
        RecyclerView mRecyclerView = binding.recyclerView;
        //RecyclerView GendersList
        RecyclerView listView = binding.recyclerViewGenders;

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

        podcastGenderAdapter = new PodcastGenderAdapter(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return false;
            }
        },this);

        podcastViewModel.getList().observe(this, new Observer<ArrayList<PodcastResponse>>() {

            @Override
            public void onChanged(ArrayList arrayList) {
                mPodcastAdapter.submitList(arrayList);



                ArrayList<String> uniqueGenderList = podcastViewModel.getTypeOfGenders(podcastViewModel.getList().getValue());
                if( uniqueGenderList.size() != podcastViewModel.getGendersSize() ){

                    podcastGenderAdapter.submitList(uniqueGenderList);


                }

            }
        });

        binding.iedtSearchTopPodcast.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TAG", "onTextChanged: ");
                podcastViewModel.updateList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mRecyclerView.setAdapter(mPodcastAdapter);
        listView.setAdapter(podcastGenderAdapter);
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(podcastViewModel.getList().getValue() != null && podcastViewModel.getList().getValue().size() <= 0){
            binding.iedtSearchTopPodcast.setText("");
        }
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
        String id = podcastViewModel.getList().getValue().get(position).getId().getAttributes().getId();
        String name = podcastViewModel.getList().getValue().get(position).getName().getLabel();
        String author = podcastViewModel.getList().getValue().get(position).getArtist().getLabel();
        imgTrack = podcastViewModel.getList().getValue().get(position).getImage().get(2).getLabel();
        //todo open datail
        Log.d("TAG_navigate", "onClick:   ---> " + id);

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_content_main);


        Bundle bundle = new Bundle();
        bundle.putString("podcastId", id);
        bundle.putString("imgTrack", imgTrack);
        bundle.putString("name", name);
        bundle.putString("author", author);
        navController.navigate(R.id.nav_podcast_detail,bundle);
    }

    @Override
    public void onGenderClick(String gender) {
        Log.d("TAG", "onClick gender:   ---> " + gender);
        podcastViewModel.updateListByGender(gender);

    }
}