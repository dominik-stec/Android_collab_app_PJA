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

import java.util.ArrayList;

public class PartsFragment extends Fragment {

    private PartsViewModel _partsViewModel;
    private FragmentPartsBinding _binding;

    // Add RecyclerView
    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        _binding = FragmentPartsBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();

        _partsViewModel = new ViewModelProvider(this).get(PartsViewModel.class);

        ArrayList<PartSingleItem> exampleList = new ArrayList<>();
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Plate 2 x 3", "3021"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Brick 2 x 4", "3001 / 15589"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Slope 1 x 2", "3040 / 6270"));
        exampleList.add(new PartSingleItem(R.drawable.ic_baseline_web_asset_24, "Tile 2 x 2 with Groove", "3068 / 63327"));

        _recyclerView = root.findViewById(R.id.partsRecyclerView);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(root.getContext());
        _adapter = new PartsAdapter(exampleList);
        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}