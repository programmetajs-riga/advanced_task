package com.example.dev_task_advanced.ui.place;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.FragmentPlaceBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceFragment extends Fragment {

    private FragmentPlaceBinding binding;
    ImageView search;
    TextView titleToolbar;
    ImageView backBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlaceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        toolBarConfig();

        googleMap();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void googleMap(){
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
    }

    public void toolBarConfig() {
        search = binding.include.search;
        search.setVisibility(View.INVISIBLE);

        backBtn = binding.include.btnBack;
        search.setVisibility(View.INVISIBLE);

        titleToolbar = binding.include.tollbarTitle;
        titleToolbar.setText(getText(R.string.title_toolbar_place));

    }
}