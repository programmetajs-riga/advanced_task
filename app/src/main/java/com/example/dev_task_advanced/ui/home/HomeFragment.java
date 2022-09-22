package com.example.dev_task_advanced.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.DTOs.CityDTO;
import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.activity.MainActivity;
import com.example.dev_task_advanced.adapters.AdapterHomeList;
import com.example.dev_task_advanced.adapters.MyCustomPagerAdapter;
import com.example.dev_task_advanced.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    int images[] = {R.drawable.box_image, R.drawable.box_fight , R.drawable.box};
    MyCustomPagerAdapter myCustomPagerAdapter;
    ArrayList<LocationDTO> locationDTOS = null;
    ArrayList<CityDTO> city = null;
    Timer timer;
    String[] citys = {"Riga" , "Jurmala"};
    Spinner spinner;
    Handler handler;
    LinearLayout sliderPanel;
    TextView titleToolbar;
    TextView openMap;
    ImageView backBtn;
    ImageView searchBtn;
    EditText searchText;
    private int dotsCount;
    private ImageView[] dots;
    int viewPagerLenght;
    int cityID = 0;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding();

        toolBarConfig();

        customPagerAdapter();

        cityDTO();

        locationDTOS();

        openMap();

        search();

        AdapterHomeList adapterHomeList = new AdapterHomeList(getActivity().getApplicationContext(), locationDTOS);
        RecyclerView recyclerView = (RecyclerView) binding.listView;
        recyclerView.setAdapter(adapterHomeList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        viewPagerSlider();

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setScrollGesturesEnabled(false);
                MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity().getApplicationContext(), R.raw.google_map);
                googleMap.setMapStyle(mapStyleOptions);
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng marker = new LatLng(56.9600, 24.0997);
                    markerOptions.position(marker);
                    markerOptions.title("here");
                    googleMap.addMarker(markerOptions);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 12));
            }
        });

        spinner();

        View root = binding.getRoot();


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                String test ;
                test = "test";
            }
        };
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        timer.cancel();
    }

    public void spinner(){
        ArrayAdapter aa = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,citys);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    public void search() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
                Toast.makeText(getContext(), "Открой глаза!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void toolBarConfig() {
        backBtn.setVisibility(View.INVISIBLE);
        titleToolbar.setVisibility(View.GONE);
        searchText.setVisibility(View.VISIBLE);

    }

    public void binding() {
        spinner = (Spinner) binding.spinner;
        backBtn = binding.include.btnBack;
        titleToolbar = binding.include.tollbarTitle;
        searchText = (EditText) binding.include.searchEditText;
        searchBtn = (ImageView) binding.include.search;
        openMap = (TextView) binding.openMap;
        viewPager = (ViewPager) binding.viewPager;
        sliderPanel = (LinearLayout) binding.sliderDots;
    }

    public void locationDTOS() {
        try {
            locationDTOS = getSports();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cityDTO() {
        try {
            city = getCity();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void openMap() {
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).navToPlace();
            }
        });
    }

    public void customPagerAdapter() {
        myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        dotsCount = myCustomPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderPanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void viewPagerSlider() {
        handler = new Handler();
        viewPagerLenght = binding.viewPager.getCurrentItem();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            viewPager.setCurrentItem(viewPagerLenght, true);
                            viewPagerLenght++;
                            if (viewPagerLenght == myCustomPagerAdapter.getCount() + 1) {
                                viewPagerLenght = 0;
                            }
                    }

                });
            }
        }, 0, 5000);
    }

    public ArrayList<CityDTO> getCity() throws JSONException {

        return CityDTO.getValue(new HTTP()
                .baseUrl(Constants.baseUrl + "getCities")
                .metode("GET")
                .connect()
                .content);
    }

    private ArrayList<LocationDTO> getSports() throws JSONException {
        String id = city.get(cityID).cityId;

        return LocationDTO.GetValue(new HTTP()
                .baseUrl(Constants.baseUrl + "getSports")
                .metode("GET")
                .connect()
                .content);
    }

}