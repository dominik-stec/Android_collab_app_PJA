package com.example.mylego.ui.sets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.databinding.FragmentSetsBinding;
import com.example.mylego.R;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class SetsFragment extends Fragment {

    private SetsViewModel setsViewModel;
    private FragmentSetsBinding binding;

    // Add RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Add example list
        ArrayList<SetsSingleItem> exampleList = new ArrayList<>();
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10295 Porsche 911", "2021", "1458"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10281 Bonsai Tree", "2021", "878"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10289 Bird of Paradise", "2021", "1173"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10280 Flower Bouquet", "2021", "756"));


        setsViewModel =
                new ViewModelProvider(this).get(SetsViewModel.class);

        binding = FragmentSetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSets;

        setsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);
            }
        });

        // Create RecyclerView
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter = new SetsAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}