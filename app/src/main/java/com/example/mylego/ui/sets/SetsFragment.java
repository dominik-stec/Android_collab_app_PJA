package com.example.mylego.ui.sets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.databinding.FragmentSetsBinding;
import com.example.mylego.R;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;

public class SetsFragment extends Fragment {

    private SetsViewModel _setsViewModel;
    private FragmentSetsBinding _binding;

    // Add RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<BricksSingleSet> _allSetsFromDb;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        _setsViewModel = new ViewModelProvider(this).get(SetsViewModel.class);
        _allSetsFromDb = _setsViewModel.getSetsFromDb();

        _binding = FragmentSetsBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();

        // Add example list
        ArrayList<SetsSingleItem> exampleList = new ArrayList<>();
//        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10295", "Porsche 911", "2021", "1458"));
//        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10281", "Bonsai Tree","2021", "878"));
//        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10289", "Bird of Paradise","2021", "1173"));
//        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10280", "Flower Bouquet","2021", "756"));

        for (BricksSingleSet singleSet : _allSetsFromDb) {
            exampleList.add(bricksSingleSetAdapter(singleSet));
        }

        // Create RecyclerView
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter = new SetsAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final TextView textView = _binding.textSets;

//        _setsViewModel.getImages().observe(getViewLifecycleOwner(), new Observer<ArrayList<ImageView>>() {
//            @Override
//            public void onChanged(@Nullable ArrayList<ImageView> imageViews) {
//            }
//        });

        _setsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    public SetsSingleItem bricksSingleSetAdapter(BricksSingleSet singleSet) {
        SetsSingleItem result = new SetsSingleItem();
        result.setSetNum(singleSet.getSet_number());
        result.setSetName(singleSet.getName());
        result.setSetYear(String.valueOf(singleSet.getYear()));
        result.setImageUrl(singleSet.getImage_url());
        result.setImageResource(R.drawable.ic_baseline_web_asset_24);

        return result;
    }

//    public void searchForSetBySetName(String name) {
//        String result = _setsViewModel.getSingleSetAsMapBySetName(name).get("name");
//        Log.d("SETS-FRAGMENT:",String.format("Search result: %s", result));
//    }
//
//    public void converterTest() {
//        _setsViewModel.convertMapSetToBricksSingleSet(_setsViewModel.getSingleSetAsMapBySetName("Gears"));
//    }
}