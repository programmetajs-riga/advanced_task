package com.example.dev_task_advanced.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
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

import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.adapters.AdapterHomeList;
import com.example.dev_task_advanced.adapters.MyCustomPagerAdapter;
import com.example.dev_task_advanced.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
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
    int viewPagerLenght;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        toolBarConfig();

        binding();

        customPagerAdapter();

        adapterHomeList();

        viewPagerSlider();

        map();

        View root = binding.getRoot();
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

    public void binding(){
        viewPager = (ViewPager) binding.viewPager;
        sliderPanel = (LinearLayout) binding.sliderDots;
    }

    public void adapterHomeList (){
        try {
            locationDTOS = getSports();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterHomeList adapterHomeList = new AdapterHomeList(locationDTOS);
        binding.listView.setAdapter(adapterHomeList);
    }
    
    public void customPagerAdapter(){
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
    }

    public void map(){
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MarkerOptions markerOptions = new MarkerOptions();
                LatLng marker = new LatLng(56.9600, 24.0997);
                markerOptions.position(marker);
                markerOptions.title("here");
                googleMap.addMarker(markerOptions);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker,12 ));
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
    }

    public void viewPagerSlider(){
        handler = new Handler();
        viewPagerLenght = binding.viewPager.getCurrentItem();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            viewPager.setCurrentItem(viewPagerLenght,true);
                            viewPagerLenght++;
                            if(viewPagerLenght == myCustomPagerAdapter.getCount()+1){
                                viewPagerLenght =0;
                            }

                        }catch (Exception e){

                        }


                    }

                });
            }
        },5000,5000);
    }

    private ArrayList<LocationDTO> getSports() throws JSONException {

        return LocationDTO.GetValue(new HTTP()
                .baseUrl(Constants.baseUrl + "getLocationsByCity")
                .metode("GET")
                .query("cityId=1")
                .connect()
                .content);
    }

}