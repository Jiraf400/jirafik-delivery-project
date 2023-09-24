package com.jirafik.delivery.services;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jirafik.delivery.activities.PhoneVerificationActivity;
import com.jirafik.delivery.activities.UserLoginActivity;
import com.jirafik.delivery.activities.UserRegisterActivity;

public class AuthenticationService {

    Context context;
    String phoneNumber;

    public AuthenticationService(Context context) {
        this.context = context;
    }

    public void enterPhoneNumber(EditText et_pv_phone, EditText et_pv_code,
                                 TextView tv_enter_phone, TextView tv_notification) {

        if (tv_enter_phone.getText().toString().contains("Enter verification code")) {
            if (et_pv_code.getText().toString().equals("123456")) {
//HERE SHOULD BE LOGIC FOR SENDING PHONE TO SERVER

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

//    boolean isUserExists(String phone) {
//
//        return false;
//    }


//    void startNextActivity(boolean isUserExists) {
//        if (isUserExists) startActivity(new Intent(this, UserLoginActivity.class));
//        else startActivity(context, new Intent(context, UserRegisterActivity.class));
//
//    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
