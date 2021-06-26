package com.example.mylego.ui.sets;

import android.net.Uri;
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

import com.example.mylego.databinding.FragmentSetsBinding;
import com.example.mylego.R;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SetsFragment extends Fragment {

    private SetsViewModel _setsViewModel;
    private FragmentSetsBinding _binding;
    private ArrayList<BricksSingleSet> _allSetsFromDb;
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
        _binding = FragmentSetsBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();

        _setsViewModel = new ViewModelProvider(this).get(SetsViewModel.class);
        _allSetsFromDb = _setsViewModel.getSetsFromDb();

        // recyclerViewSetsList.add(new SetsSingleItem(R.drawable.ic_baseline_web_asset_24, "10295", "Porsche 911", "2021", "1458"));
        ArrayList<SetsSingleItem> recyclerViewSetsList = new ArrayList<>();
        for (BricksSingleSet singleSet : _allSetsFromDb) {
            recyclerViewSetsList.add(bricksSingleSetAdapter(singleSet, _thumbnailsPathsList));
        }

        _recyclerView = root.findViewById(R.id.recyclerView);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(root.getContext());
        _adapter = new SetsAdapter(recyclerViewSetsList);
        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        _setsViewModel.getThumbnailsPathsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Uri>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Uri> uriList) {
                _thumbnailsPathsList = uriList;
                recyclerViewSetsList.clear();
                for (BricksSingleSet singleSet : _allSetsFromDb) {
                    recyclerViewSetsList.add(bricksSingleSetAdapter(singleSet, _thumbnailsPathsList));
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
    public SetsSingleItem bricksSingleSetAdapter(BricksSingleSet singleSet, ArrayList<Uri> thumbnailList) {
        SetsSingleItem result = new SetsSingleItem();

        result.setSetNum(singleSet.getSet_number());
        result.setSetName(singleSet.getName());
        result.setSetYear(String.valueOf(singleSet.getYear()));
        result.setSetNumParts(String.valueOf(singleSet.getNumber_of_parts()));
        result.setImageUrl(singleSet.getImage_url());
        result.setImageResource(R.drawable.ic_noun_lego_brick_847002);

        if (thumbnailList != null) {
            //Log.d("SetsFragment-bricksSingleSetAdapter", String.format("Uri list NOT NULL"));
            Uri thPath = findThumbnailPath(singleSet.getSet_number(), thumbnailList);
            result.setThumbnailPath(thPath);
        }

        return result;
    }

    //==============================================================================================
    public Uri findThumbnailPath(String setNumToMatch, ArrayList<Uri> thumbnailList) {
        return thumbnailList
                .stream()
                .filter(th -> th.getPath().contains(setNumToMatch))
                .collect(Collectors.toList())
                .get(0);
    }
    //==============================================================================================
//    public void logThumbnailsPaths(ArrayList<Uri> thumbnails) {
//        for (Uri th : thumbnails) {
//            Log.d("SetsFragment-logThumbnailsPaths", String.format("Thumbnail Path: %s", th.getPath()));
//        }
//    }

//    public void searchForSetBySetName(String name) {
//        String result = _setsViewModel.getSingleSetAsMapBySetName(name).get("name");
//        Log.d("SETS-FRAGMENT:",String.format("Search result: %s", result));
//    }
//
//    public void converterTest() {
//        _setsViewModel.convertMapSetToBricksSingleSet(_setsViewModel.getSingleSetAsMapBySetName("Gears"));
//    }
}