package com.example.dev_task_advanced.ui.settings;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.activity.LanguageActivity;
import com.example.dev_task_advanced.activity.LoginActivity;
import com.example.dev_task_advanced.activity.MainActivity;
import com.example.dev_task_advanced.databinding.FragmentServicesBinding;
import com.example.dev_task_advanced.databinding.FragmentSettingsBinding;
import com.example.dev_task_advanced.ui.home.HomeFragment;
import com.example.dev_task_advanced.ui.services.ServicesViewModel;

public class fragment_settings extends Fragment {


    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView languageSelet = (TextView) binding.selectLanguage;

        languageSelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent);

//                Fragment fragment = new HomeFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment_activity_main ,fragment);
//                transaction.commit();
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