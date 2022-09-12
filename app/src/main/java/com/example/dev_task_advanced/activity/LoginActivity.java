package com.example.dev_task_advanced.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.dev_task_advanced.Constants;
import com.example.dev_task_advanced.HTTP;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static Activity fa;

    int responseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matcher matcher;
                //not space
                Pattern patternSpace = Pattern.compile("[/ ]*");
                //allow all but not SPECIAL symbol
                Pattern patternPassword = Pattern.compile("^[a-zA-Z0-9!@#^.?]*$");
                Pattern patternLogin = Pattern.compile("[A-Za-z0-9]*");
                String login = binding.login.getText().toString();
                String password = binding.password.getText().toString();

                if(login.equals("") && password.equals("")){
                   binding.errortext.setText(getResources().getString(R.string.vailidation));
                }else if (patternSpace.matcher(password).matches()){
                   binding.errortext.setText("empty password or validation error");
               }else if (patternSpace.matcher(login).matches()){
                    binding.errortext.setText("empty login or validation error ");
                }else if (!patternPassword.matcher(password).matches() || !patternLogin.matcher(login).matches()){
                    binding.errortext.setText("validation error");
                }
               else{
                   responseCode = getLogin();
                   if(responseCode == 200){
                       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(intent);
                   }
                   else if (responseCode == 401){
                       binding.errortext.setText(getResources().getString(R.string.error401));
                   }else if(responseCode == 404){
                       binding.errortext.setText(getResources().getString(R.string.error404));
                   }else if(responseCode == 0){
                       binding.errortext.setText("check internet connection");
                   }else{
                       binding.errortext.setText(getResources().getString(R.string.vailidation));
                   }
               }
            }
        });

        validationLogin();

        validationPassword();
    }

    private void validationPassword(){
        binding.password.addTextChangedListener(new TextWatcher() {
            String password;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = binding.password.getText().toString();
                //allow all but not SPECIAL symbol
                Pattern patternPassword = Pattern.compile("^[a-zA-Z0-9!@#^.?]*$");
                if(!patternPassword.matcher(password).matches()){
                    binding.errortext.setText("use only letter,number or symbol!@#^.?");
                }else{
                    binding.errortext.setText("");
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validationLogin(){
        binding.login.addTextChangedListener(new TextWatcher() {
            String login;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login = binding.login.getText().toString();
                Pattern patternLogin = Pattern.compile("[A-Za-z0-9]*");
                if(!patternLogin.matcher(login).matches()){
                    binding.errortext.setText("use only letter or number");
                }else{
                    binding.errortext.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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