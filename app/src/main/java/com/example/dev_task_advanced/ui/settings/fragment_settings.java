package com.example.dev_task_advanced.ui.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.activity.LanguageActivity;
import com.example.dev_task_advanced.databinding.FragmentSettingsBinding;

import java.util.Locale;

public class fragment_settings extends Fragment {
    ImageView search;
    TextView titleToolbar;

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        toolBarConfig();


        TextView languageSelect = (TextView) binding.selectLanguage;


        languageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent);

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


        search = binding.include.search;
        search.setVisibility(View.INVISIBLE);

        titleToolbar = binding.include.tollbarTitle;
        titleToolbar.setText(getText(R.string.title_language));




    }
}

