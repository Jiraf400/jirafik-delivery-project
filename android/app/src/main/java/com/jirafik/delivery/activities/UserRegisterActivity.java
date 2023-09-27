package com.jirafik.delivery.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jirafik.delivery.R;
import com.jirafik.delivery.services.AuthenticationService;

public class UserRegisterActivity extends AppCompatActivity {

    AuthenticationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        authService = new AuthenticationService(this);

        EditText et_register_name = findViewById(R.id.et_register_name);
        EditText et_register_email = findViewById(R.id.et_register_email);
        EditText et_register_password = findViewById(R.id.et_register_password);
        EditText et_register_confPassword = findViewById(R.id.et_register_confPassword);
        Button btn_register_submit = findViewById(R.id.btn_register_submit);

        btn_register_submit.setOnClickListener(view ->
                authService.registerUser(et_register_name, et_register_email,
                        et_register_password, et_register_confPassword,
                new AuthenticationService.UserEnterListener() {

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(UserRegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(UserRegisterActivity.this, WaitActivity.class));
                    }
                }));

    }
}