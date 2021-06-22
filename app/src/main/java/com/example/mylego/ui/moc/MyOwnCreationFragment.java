package com.example.mylego.ui.moc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentMocBinding;
import com.example.mylego.ui.parts.PartSingleItem;
import com.example.mylego.ui.parts.PartsAdapter;

import java.util.ArrayList;


public class MyOwnCreationFragment extends Fragment {

    private MyOwnCreationViewModel myOwnCreationViewModel;
    private FragmentMocBinding binding;

    // Add RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Add example list
        ArrayList<MocSingleItem> exampleList = new ArrayList<>();
        exampleList.add(new MocSingleItem(R.drawable.ic_baseline_web_asset_24, "House", "220"));
        exampleList.add(new MocSingleItem(R.drawable.ic_baseline_web_asset_24, "Crazy Rabbit", "50"));
        exampleList.add(new MocSingleItem(R.drawable.ic_baseline_web_asset_24, "Tower", "120"));
        exampleList.add(new MocSingleItem(R.drawable.ic_baseline_web_asset_24, "Moc 123", "123"));

        myOwnCreationViewModel =
                new ViewModelProvider(this).get(MyOwnCreationViewModel.class);

        binding = FragmentMocBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMoc;
        myOwnCreationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Create RecyclerView
        mRecyclerView = root.findViewById(R.id.mocRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter = new MocAdapter(exampleList);
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