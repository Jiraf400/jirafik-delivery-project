package com.jirafik.delivery.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jirafik.delivery.R;
import com.jirafik.delivery.services.AuthenticationService;

public class UserLoginActivity extends AppCompatActivity {

    AuthenticationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        authService = new AuthenticationService(this);

        EditText et_login_email = findViewById(R.id.et_login_email);
        EditText et_login_password = findViewById(R.id.et_login_password);
        Button btn_login_submit = findViewById(R.id.btn_login_submit);


        btn_login_submit.setOnClickListener(view -> {
            authService.loginUser(et_login_email, et_login_password, new AuthenticationService.UserEnterListener() {
                @Override
                public void onError(String msg) {

                }

                @Override
                public void onSuccess() {
                    startActivity(new Intent(UserLoginActivity.this, WaitActivity.class));
                }
            });

        });
    }
}