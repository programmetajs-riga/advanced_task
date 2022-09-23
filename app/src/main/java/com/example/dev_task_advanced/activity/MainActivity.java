package com.example.dev_task_advanced.activity;

import android.Manifest;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.ActivityMainBinding;
import com.example.dev_task_advanced.ui.home.HomeFragment;
import com.example.dev_task_advanced.ui.myRecords.MyRecordsFragment;
import com.example.dev_task_advanced.ui.place.PlaceFragment;
import com.example.dev_task_advanced.ui.services.ServicesFragment;
import com.example.dev_task_advanced.ui.settings.fragment_settings;


public class MainActivity extends AppCompatActivity {

    public static Activity mainActivity;

    private HomeFragment homeFragment;
    private PlaceFragment placeFragment;
    private ServicesFragment servicesFragment;
    private MyRecordsFragment recordsFragment;
    private fragment_settings settingsFragment;

    private LinearLayout homeLayout;
    private LinearLayout placeLayout;
    private LinearLayout recordsLayout;
    private LinearLayout servicesLayout;
    private LinearLayout settingsLayout;

    private LinearLayout navHomeBtn;
    private LinearLayout navPlaceBtn;
    private LinearLayout navServicesBtn;
    private LinearLayout navRecordsBtn;
    private LinearLayout navSettingsBtn;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mainActivity = this;

        LoginActivity.loginActivity.finish();

        bottonNavBinding();

        layoutBinding();

       fragmentInit();

       fragmentManager();

        navOnClickListener();

    }

    private void fragmentInit(){
        homeFragment = new HomeFragment();
        placeFragment = new PlaceFragment();
        servicesFragment = new ServicesFragment();
        recordsFragment = new MyRecordsFragment();
        settingsFragment = new fragment_settings();
    }

    private void fragmentManager(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.linear_fragment_home, homeFragment , homeFragment.getTag())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.linear_fragment_place, placeFragment , placeFragment.getTag())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.linear_fragment_services, servicesFragment , servicesFragment.getTag())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.linear_fragment_records, recordsFragment , recordsFragment.getTag())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.linear_fragment_settings, settingsFragment , settingsFragment.getTag())
                .commit();
    }

    private void layoutBinding(){
        homeLayout = binding.linearFragmentHome;
        placeLayout = binding.linearFragmentPlace;
        servicesLayout = binding.linearFragmentServices;
        recordsLayout = binding.linearFragmentRecords;
        settingsLayout = binding.linearFragmentSettings;
    }

    private void bottonNavBinding(){
        navHomeBtn = findViewById(R.id.nav_btn_home);
        navPlaceBtn = findViewById(R.id.nav_btn_place);
        navServicesBtn = findViewById(R.id.nav_btn_services);
        navRecordsBtn = findViewById(R.id.nav_btn_records);
        navSettingsBtn = findViewById(R.id.nav_btn_settings);
    }

    private void backColorNav(){
        navHomeBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        navPlaceBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        navServicesBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        navRecordsBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        navSettingsBtn.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    private void hideAllFragment(){
        homeLayout.setVisibility(View.GONE);
        placeLayout.setVisibility(View.GONE);
        servicesLayout.setVisibility(View.GONE);
        recordsLayout.setVisibility(View.GONE);
        settingsLayout.setVisibility(View.GONE);
    }

    public void navToPlace(){
        backColorNav();
        hideAllFragment();
        placeLayout.setVisibility(View.VISIBLE);
        navPlaceBtn.setBackgroundColor(Color.parseColor("#DADADA"));
    }

    public void navToHome(){
        backColorNav();
        hideAllFragment();
        homeLayout.setVisibility(View.VISIBLE);
        navHomeBtn.setBackgroundColor(Color.parseColor("#DADADA"));
    }

    public void navToServices(){
        backColorNav();
        hideAllFragment();
        servicesLayout.setVisibility(View.VISIBLE);
        navServicesBtn.setBackgroundColor(Color.parseColor("#DADADA"));
    }

    public void navToRecords(){
        backColorNav();
        hideAllFragment();
        recordsLayout.setVisibility(View.VISIBLE);
        navRecordsBtn.setBackgroundColor(Color.parseColor("#DADADA"));
    }

    public void navToSettings(){
        backColorNav();
        hideAllFragment();
        settingsLayout.setVisibility(View.VISIBLE);
        navSettingsBtn.setBackgroundColor(Color.parseColor("#DADADA"));
    }


    private void navOnClickListener(){
        navHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToHome();
            }
        });

        navPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToPlace();
            }
        });

        navServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToServices();
            }
        });

        navRecordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToRecords();
            }
        });

        navSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToSettings();
            }
        });

    }

}