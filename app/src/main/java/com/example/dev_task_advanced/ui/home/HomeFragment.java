package com.example.dev_task_advanced.ui.home;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    int images[] = {R.drawable.box , R.drawable.box_fight};
    MyCustomPagerAdapter myCustomPagerAdapter;
    Timer timer;
    Handler handler;

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

        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i = binding.viewPager.getCurrentItem();
                        i++;
                        viewPager.setCurrentItem(i,true);
                    }
                });
            }
        },5000,5000);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return root;

        //todo map(google libaries)

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}