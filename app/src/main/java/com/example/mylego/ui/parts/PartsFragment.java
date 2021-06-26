package com.example.mylego.ui.parts;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentPartsBinding;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.ui.sets.SetsSingleItem;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PartsFragment extends Fragment {

    private PartsViewModel _partsViewModel;
    private FragmentPartsBinding _binding;
    private ArrayList<Part> _allPartsFromDb;
    private ArrayList<Uri> _thumbnailsPathsList;

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
        _allPartsFromDb = _partsViewModel.getPartsFromDb();

        // recyclerViewSetsList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10295", "Porsche 911", "2021", "1458"));
        ArrayList<PartSingleItem> recyclerViewPartsList = new ArrayList<>();
        for (Part singlePart : _allPartsFromDb) {
            recyclerViewPartsList.add(singlePartAdapter(singlePart, _thumbnailsPathsList));
        }

        _recyclerView = root.findViewById(R.id.partsRecyclerView);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(root.getContext());
        _adapter = new PartsAdapter(recyclerViewPartsList);
        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        _partsViewModel.getThumbnailsPathsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Uri>>() {
            @Override
            public void onChanged(ArrayList<Uri> uris) {
                _thumbnailsPathsList = uris;
                recyclerViewPartsList.clear();
                for (Part singlePart : _allPartsFromDb) {
                    recyclerViewPartsList.add(singlePartAdapter(singlePart, _thumbnailsPathsList));
                }
                _adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    //==============================================================================================
    public PartSingleItem singlePartAdapter(Part singlePart, ArrayList<Uri> thumbnailList) {
        PartSingleItem result = new PartSingleItem();
        result.setPartNumber(singlePart.getPart_num());
        result.setPartName(singlePart.getName());
        result.setImageUrl(singlePart.getPart_img_url());
        result.setImageResource(R.drawable.ic_baseline_web_asset_24);

        if (thumbnailList != null) {
            //Log.d("SetsFragment-bricksSingleSetAdapter", String.format("Uri list NOT NULL"));
            Uri thPath = findThumbnailPath(singlePart.getPart_num(), thumbnailList);
            result.setThumbnailPath(thPath);
        }

        return result;
    }

    //==============================================================================================
    public Uri findThumbnailPath(String patternToMatch, ArrayList<Uri> thumbnailList) {
        return thumbnailList
                .stream()
                .filter(th -> th.getPath().contains(patternToMatch))
                .collect(Collectors.toList())
                .get(0);
    }
}