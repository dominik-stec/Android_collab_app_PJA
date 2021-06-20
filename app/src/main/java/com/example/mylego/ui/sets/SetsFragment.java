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

import com.example.mylego.databinding.FragmentSetsBinding;

import java.lang.reflect.InvocationTargetException;

public class SetsFragment extends Fragment {

    private SetsViewModel _setsViewModel;
    private FragmentSetsBinding _binding;

    //private ArrayList<Map<String, String>> _setsFromDbSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _setsViewModel = new ViewModelProvider(this).get(SetsViewModel.class);
        //_setsFromDbSearch = _setsViewModel.setsFromDbSearch;

        _binding = FragmentSetsBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();

        final TextView textView = _binding.textSets;

        _setsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

//    public void searchForSetBySetName(String name) {
//        String result = _setsViewModel.getSingleSetAsMapBySetName(name).get("name");
//        Log.d("SETS-FRAGMENT:",String.format("Search result: %s", result));
//    }
//
//    public void converterTest() {
//        _setsViewModel.convertMapSetToBricksSingleSet(_setsViewModel.getSingleSetAsMapBySetName("Gears"));
//    }
}