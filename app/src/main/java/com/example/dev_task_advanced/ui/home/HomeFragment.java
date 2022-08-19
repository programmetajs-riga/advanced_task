package com.example.dev_task_advanced.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.dev_task_advanced.MyCustomPagerAdapter;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    int images[] = {R.drawable.box};
    MyCustomPagerAdapter myCustomPagerAdapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRecordsViewModel homeViewModel =
                new ViewModelProvider(this).get(MyRecordsViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);


        viewPager = (ViewPager) binding.viewPager;

        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        View root = binding.getRoot();
        return root;
        //TODO viewpager!!
        //todo map(google libaries)

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}