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

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.databinding.FragmentSetsBinding;

import java.util.HashMap;
import java.util.Map;

public class SetsFragment extends Fragment {

    private SetsViewModel setsViewModel;
    private FragmentSetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}