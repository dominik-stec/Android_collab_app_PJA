package com.example.mylego.ui.moc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylego.R;
import com.example.mylego.databinding.FragmentDashboardBinding;
import com.example.mylego.databinding.FragmentHomeBinding;
import com.example.mylego.ui.dashboard.DashboardViewModel;
import com.example.mylego.ui.home.HomeViewModel;


public class MyOwnCreationFragment extends Fragment {

    private MyOwnCreationViewModel myOwnCreationViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myOwnCreationViewModel =
                new ViewModelProvider(this).get(MyOwnCreationViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        myOwnCreationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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