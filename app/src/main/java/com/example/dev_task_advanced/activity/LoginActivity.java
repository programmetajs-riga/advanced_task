package com.example.dev_task_advanced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.regex.Pattern;

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
                //Minimum eight characters, at least one letter and one number:
                Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
                //Minimum eight characters, at least one letter, one number and one special character:
                Pattern pattern1 = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
                //Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
                Pattern pattern2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
                //Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
                Pattern pattern3 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
                //Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character:
                Pattern pattern4 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$");
                String login = binding.login.getText().toString();
                String password = binding.password.getText().toString();
                if(login.equals("") && password.equals("")){
                   binding.errortext.setText(getResources().getString(R.string.vailidation));
                }else if (login.contains(" ") || password.contains(" ")){
                   binding.errortext.setText("no space allow");
               }
               else if(login.equals("") || login.equals(" ")){
                   binding.errortext.setText("pls enter login");
               }else if(password.equals("") || password.equals(" ")){
                   binding.errortext.setText("pls enter password");
               }
               else{
                   responseCode = getLogin();
                   if(responseCode == 200){
                       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       startActivity(intent);
                   }
                   else if (responseCode == 401){
                       binding.errortext.setText(getResources().getString(R.string.error401));
                   }else if(responseCode == 404){
                       binding.errortext.setText(getResources().getString(R.string.error404));
                   }else{
                       binding.errortext.setText(getResources().getString(R.string.vailidation));
                   }
               }
            }
        });

    }
    private boolean validation(){
        return binding.login.getText().toString() == null &&  binding.password.getText().toString() == null &&
                binding.login.getText().toString() == "" &&  binding.password.getText().toString() == "";
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