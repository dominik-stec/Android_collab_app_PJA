package com.example.mylego.ui.moc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentMocBinding;
import com.example.mylego.ui.moc.MocAdapter;

import java.util.ArrayList;


public class MyOwnCreationFragment extends Fragment {

    private MyOwnCreationViewModel myOwnCreationViewModel;
    private FragmentMocBinding binding;

    // Add RecyclerView
    private RecyclerView mRecyclerView;
    private MocAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MocSingleItem> exampleList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Add example list
        exampleList = new ArrayList<>();
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

        EditText editText = root.findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return root;
    }


    private void filter(String text) {
        ArrayList<MocSingleItem> filteredList = new ArrayList<>();

        for (MocSingleItem item : exampleList) {
            if (item.getMocName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}