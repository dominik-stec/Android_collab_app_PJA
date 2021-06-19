package com.example.mylego.ui.parts;

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
import com.example.mylego.databinding.FragmentPartsBinding;
import com.example.mylego.ui.sets.SetsAdapter;
import com.example.mylego.ui.sets.SetsSingleItem;

import java.util.ArrayList;

public class PartsFragment extends Fragment {

    private PartsViewModel partsViewModel;
    private FragmentPartsBinding binding;

    // Add RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Add example list
        ArrayList<PartSingleItem> exampleList = new ArrayList<>();
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Plate 2 x 3", "3021"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Brick 2 x 4", "3001 / 15589"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Slope 1 x 2", "3040 / 6270"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Tile 2 x 2 with Groove", "3068 / 63327"));

        partsViewModel =
                new ViewModelProvider(this).get(PartsViewModel.class);

        binding = FragmentPartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textParts;
        partsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Create RecyclerView
        mRecyclerView = root.findViewById(R.id.partsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter = new PartsAdapter(exampleList);
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