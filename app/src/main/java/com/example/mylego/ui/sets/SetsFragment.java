package com.example.mylego.ui.sets;

import android.os.Bundle;
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

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentSetsBinding;

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
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 1", "Line 2"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 3", "Line 4"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 5", "Line 6"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 7", "Line 8"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 9", "Line 10"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 11", "Line 12"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 13", "Line 14"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 15", "Line 16"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 17", "Line 18"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 19", "Line 20"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 21", "Line 22"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 23", "Line 24"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 25", "Line 26"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 27", "Line 28"));
        exampleList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "Line 29", "Line 30"));


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