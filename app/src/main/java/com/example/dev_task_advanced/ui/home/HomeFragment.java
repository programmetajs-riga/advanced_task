package com.example.dev_task_advanced.ui.home;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.dev_task_advanced.adapters.AdapterHomeList;
import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.adapters.MyCustomPagerAdapter;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    int images[] = {R.drawable.box , R.drawable.box_fight};
    MyCustomPagerAdapter myCustomPagerAdapter;
    ArrayList<LocationDTO> locationDTOS = null;
    Timer timer;
    Handler handler;
    LinearLayout sliderPanel;
    TextView titleToolbar;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private int dotsCount;
    private ImageView[] dots;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);

        toolBarConfig();

       viewPager = (ViewPager) binding.viewPager;
       sliderPanel = (LinearLayout) binding.sliderDots;

        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        dotsCount = myCustomPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for( int i = 0 ; i < dotsCount ; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0 ,8 , 0);

            sliderPanel.addView(dots[i], params);
        }

         dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0 ; i < dotsCount ; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        try {
            locationDTOS = getSports();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterHomeList adapterHomeList = new AdapterHomeList(getContext(),locationDTOS);
        binding.listView.setAdapter(adapterHomeList);

        View root = binding.getRoot();


        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int i = binding.viewPager.getCurrentItem();
                            i++;
                            viewPager.setCurrentItem(i,true);
                        }catch (Exception e){

                        }


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

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void toolBarConfig() {

        titleToolbar = binding.include.tollbarTitle;
        titleToolbar.setVisibility(View.INVISIBLE);

    }

    private ArrayList<LocationDTO> getSports() throws JSONException {

        return LocationDTO.GetValue(new HTTP()
                .baseUrl(Constants.baseUrl + "getLocationsByCity?cityId=1")
                .metode("GET")
                .connect()
                .content);
    }

}