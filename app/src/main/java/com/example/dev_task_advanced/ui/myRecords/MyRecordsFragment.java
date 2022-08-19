package com.example.dev_task_advanced.ui.myRecords;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.example.dev_task_advanced.databinding.FragmentMyRecordsBinding;


public class MyRecordsFragment extends Fragment {

    private FragmentMyRecordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRecordsViewModel myRecordsViewModel =
                new ViewModelProvider(this).get(MyRecordsViewModel.class);

        binding = FragmentMyRecordsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyRecords;
        myRecordsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}