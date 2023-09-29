package com.jirafik.delivery.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jirafik.delivery.R;
import com.jirafik.delivery.services.AuthenticationService;

import java.util.Timer;
import java.util.TimerTask;

public class PhoneVerificationActivity extends AppCompatActivity {

    Button btn_phone_submit, btn_code_submit;
    TextView tv_enter_phone, tv_notification;
    EditText et_pv_phone, et_pv_code;
    String sentCode;

    AuthenticationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        btn_phone_submit = findViewById(R.id.btn_phone_submit);
        btn_code_submit = findViewById(R.id.btn_code_submit);
        et_pv_phone = findViewById(R.id.et_pv_phone);
        et_pv_code = findViewById(R.id.et_pv_code);
        tv_enter_phone = findViewById(R.id.tv_enter_phone);
        tv_notification = findViewById(R.id.tv_notification);
        authService = new AuthenticationService(this);

        btn_phone_submit.setOnClickListener(view ->
                authService.checkEnteredPhoneNumber(et_pv_phone, et_pv_code,
                        tv_enter_phone, tv_notification, btn_phone_submit, btn_code_submit));

        btn_code_submit.setOnClickListener(view -> authService.verifySentCode(et_pv_code,
                new AuthenticationService.UserAuthListener() {
                    @Override
                    public void onLogin() {
                        startActivity(new Intent(getApplicationContext(), UserLoginActivity.class));
                    }

                    @Override
                    public void onRegister() {
                        startActivity(new Intent(getApplicationContext(), UserRegisterActivity.class));
                    }
                }));

    }
}










