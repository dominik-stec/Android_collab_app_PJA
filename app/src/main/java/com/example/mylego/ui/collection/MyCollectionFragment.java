package com.example.mylego.ui.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentMyCollectionBinding;

public class MyCollectionFragment extends Fragment {

    private MyCollectionViewModel _myCollectionViewModel;
    private FragmentMyCollectionBinding _binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        _myCollectionViewModel = new ViewModelProvider(this).get(MyCollectionViewModel.class);

        _binding = FragmentMyCollectionBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();

        final TextView ownedSets = root.findViewById(R.id.text_sets_number);
        _myCollectionViewModel.getOwnSetsCount().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String setsCount) {
                ownedSets.setText(setsCount);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}