package com.example.dev_task_advanced.ui.place;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.FragmentPlaceBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceFragment extends Fragment {

    private FragmentPlaceBinding binding;
    ImageView search;
    TextView titleToolbar;
    EditText searchText;
    int saveInstance = 0;
    ImageView backBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlaceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding();

        toolBarConfig();

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (saveInstance == 0) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng marker = new LatLng(56.9600, 24.0997);
                    markerOptions.position(marker);
                    markerOptions.title("here");
                    googleMap.addMarker(markerOptions);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 12));
                    saveInstance = 1;
                } else{
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void binding(){
        searchText = binding.include.searchEditText;
        backBtn = binding.include.btnBack;
        titleToolbar = binding.include.tollbarTitle;
        search = binding.include.search;
    }

    public void toolBarConfig() {

        search.setVisibility(View.GONE);
        searchText.setVisibility(View.GONE);
        backBtn.setVisibility(View.INVISIBLE);
        titleToolbar.setVisibility(View.VISIBLE);

        titleToolbar.setText(getText(R.string.title_toolbar_place));



    }
}