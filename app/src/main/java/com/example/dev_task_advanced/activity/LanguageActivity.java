package com.example.dev_task_advanced.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dev_task_advanced.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    Button btnSubmit;
    TextView btnBack;
    TableRow enRow;
    TableRow ruRow;
    TableRow lvRow;
    String languageToLoad  = "en";
    String usedLanguage;
    TextView checkedEnLanguage;
    TextView checkedRuLanguage;
    TextView checkedLvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().hide();

        uiComponentDef();

       usedLanguage = String.valueOf(getResources().getConfiguration().locale);

       validation();

        enRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageToLoad  = "en";
                checkedRuLanguage.setVisibility(View.INVISIBLE);
                checkedLvLanguage.setVisibility(View.INVISIBLE);

                checkedEnLanguage.setVisibility(View.VISIBLE);
            }
        });

        ruRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageToLoad  = "ru";
                checkedEnLanguage.setVisibility(View.INVISIBLE);
                checkedLvLanguage.setVisibility(View.INVISIBLE);

                checkedRuLanguage.setVisibility(View.VISIBLE);
            }
        });

        lvRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageToLoad  = "lv";
                checkedRuLanguage.setVisibility(View.INVISIBLE);
                checkedEnLanguage.setVisibility(View.INVISIBLE);

                checkedLvLanguage.setVisibility(View.VISIBLE);
            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);

            }
        });
    }

    public void validation(){
        if(usedLanguage.contains("en")){
            checkedRuLanguage.setVisibility(View.INVISIBLE);
            checkedLvLanguage.setVisibility(View.INVISIBLE);

            checkedEnLanguage.setVisibility(View.VISIBLE);
        }else if (usedLanguage.contains("ru")){
            checkedEnLanguage.setVisibility(View.INVISIBLE);
            checkedLvLanguage.setVisibility(View.INVISIBLE);

            checkedRuLanguage.setVisibility(View.VISIBLE);
        }else if (usedLanguage.contains("lv")){
            checkedRuLanguage.setVisibility(View.INVISIBLE);
            checkedEnLanguage.setVisibility(View.INVISIBLE);

            checkedLvLanguage.setVisibility(View.VISIBLE);
        }
    }
    public void uiComponentDef (){
        btnSubmit = (Button) findViewById(R.id.button);
        btnBack = (TextView) findViewById(R.id.btn_back);
        enRow = (TableRow) findViewById(R.id.eng_selected_language);
        ruRow = (TableRow) findViewById(R.id.rus_selected_language);
        lvRow = (TableRow) findViewById(R.id.lv_selected_language);
        checkedEnLanguage = (TextView) findViewById(R.id.checked_en_language);
        checkedRuLanguage = (TextView) findViewById(R.id.checked_ru_language);
        checkedLvLanguage = (TextView) findViewById(R.id.checked_lv_language);
    }

}