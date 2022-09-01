package com.example.dev_task_advanced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.databinding.ActivityLoginBinding;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    int responseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(validation()){
                    responseCode = getLogin();
                }
                if(responseCode == 200){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (responseCode == 401){
                    binding.errortext.setText("Incorrect login or password");
                }else if(responseCode == 404){
                    binding.errortext.setText("Problems with connections!");
                }else{
                    binding.errortext.setText("Something going wrong");
                }




            }
        });

    }
    private boolean validation(){
        return binding.password.getText().toString() != null && binding.login.getText().toString() != null
                && binding.login.getText().toString() != "" && binding.password.getText().toString() != "";
    }


    private int getLogin() {
        HashMap<String,String> cred = new HashMap();

        cred.put("username" , binding.login.getText().toString());
        cred.put("password", binding.password.getText().toString());

        return new HTTP()
                .baseUrl(Constants.baseUrl + "login")
                .header(cred)
                .metode("POST")
                .connect()
                .responseCode;
    }



}