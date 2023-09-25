package com.jirafik.delivery.services;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AuthenticationService {

    Context context;
    String phoneNumber;

    public AuthenticationService(Context context) {
        this.context = context;
    }

    public void enterPhoneNumber(EditText et_pv_phone, EditText et_pv_code,
                                 TextView tv_enter_phone, TextView tv_notification, UserAuthListener authListener) {

        if (tv_enter_phone.getText().toString().contains("Enter verification code")) {
            if (et_pv_code.getText().toString().equals("123456")) {
                String phone = getPhoneNumber();

                //Logic to send phone to server

                authListener.onRegister();

                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
            } else {
                et_pv_code.getText().clear();
                et_pv_code.setError("Codes didn't match");
            }
        }

        if (et_pv_phone.length() < 6 || et_pv_phone.length() > 15) {
            et_pv_phone.setError("Please provide valid phone number");
            return;
        }

        String phone = "+" + et_pv_phone.getText().toString();
        setPhoneNumber(phone);
        et_pv_phone.getText().clear();
        et_pv_phone.setHint("SMS code");

        tv_enter_phone.setText("Enter verification code that we sent to " + phone);
        et_pv_phone.setVisibility(View.INVISIBLE);
        tv_notification.setVisibility(View.VISIBLE);
        et_pv_code.setVisibility(View.VISIBLE);
    }

    public void loginUser(EditText etLoginEmail, EditText etLoginPassword) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

        if (etLoginEmail.length() == 0 || !etLoginEmail.getText().toString().matches(emailPattern)) {
            etLoginEmail.setError("Email is required");
        }

        //TODO send variables to server

    }

    public void registerUser(EditText etRegisterName, EditText etRegisterEmail,
                             EditText etRegisterPassword, EditText etRegisterConfPassword) {


    }

    public interface UserAuthListener {
        void onLogin();

        void onRegister();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
