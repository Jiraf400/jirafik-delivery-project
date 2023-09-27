package com.jirafik.delivery.services;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jirafik.delivery.models.User;
import com.jirafik.delivery.models.UserToLogin;

import java.util.Random;

public class AuthenticationService {

    Context context;
    String phoneNumber;
    String verifCode;
    boolean isUserExists;

    public AuthenticationService(Context context) {
        this.context = context;
    }

    public void checkEnteredPhoneNumber(EditText et_pv_phone, EditText et_pv_code,
                                        TextView tv_enter_phone, TextView tv_notification,
                                        Button btn_phone_submit, Button btn_code_submit) {

        if (et_pv_phone.length() < 6 || et_pv_phone.length() > 15) {
            et_pv_phone.setError("Please provide valid phone number");
            return;
        }

        //logic to send phone to server
        //logic to send phone to server
        //logic to send phone to server

        setUserExists(true); //temporary hardcoded for testing
        setVerifCode(generateVerificationCode());

        String phone = "+" + et_pv_phone.getText().toString();
        setPhoneNumber(phone);

        changeVerificationViews(tv_enter_phone, tv_notification, et_pv_phone, et_pv_code,
                btn_phone_submit, btn_code_submit, phone);
    }

    public void loginUser(EditText etLoginEmail, EditText etLoginPassword, UserEnterListener userEnterListener) {

        boolean fieldsAreCorrect = checkLoginFields(etLoginEmail, etLoginPassword);

        if (fieldsAreCorrect) {
            UserToLogin user = new UserToLogin();

            user.setEmail(etLoginEmail.getText().toString());
            user.setPassword(etLoginPassword.getText().toString());

            userEnterListener.onSuccess();
        } else userEnterListener.onError("Please check all fields to be filled.");


        //logic to send user to server
        //logic to send user to server
        //logic to send user to server

    }

    public void registerUser(EditText etRegisterName, EditText etRegisterEmail,
                             EditText etRegisterPassword, EditText etRegisterConfPassword,
                             UserEnterListener userRegisterListener) {

        boolean fieldsAreCorrect = checkRegisterFields(etRegisterName, etRegisterEmail,
                etRegisterPassword, etRegisterConfPassword);

        String phone = getPhoneNumber();

        if (fieldsAreCorrect) {
            User user = new User();
            user.setEmail(etRegisterEmail.getText().toString());
            user.setName(etRegisterName.getText().toString());
            user.setPhone(phone);
            user.setPassword(etRegisterPassword.getText().toString());

            userRegisterListener.onSuccess();
        } else {
            userRegisterListener.onError("Please check all fields to be filled.");
        }

        //logic to send user to server
        //logic to send user to server
        //logic to send user to server
    }

    private boolean checkRegisterFields(EditText etRegisterUsername, EditText etRegisterEmail,
                                        EditText etRegisterPassword, EditText passConfirm) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

        if (etRegisterUsername.length() == 0) {
            etRegisterUsername.setError("This field is required");
            return false;
        }

        if (etRegisterEmail.length() == 0 || !etRegisterEmail.getText().toString().matches(emailPattern)) {
            etRegisterEmail.setError("Email is required");
            return false;
        }
        if (etRegisterPassword.length() == 0) {
            etRegisterPassword.setError("Password is required");
            return false;
        } else if (etRegisterPassword.length() < 8) {
            etRegisterPassword.setError("Password must be minimum 8 characters");
            return false;
        } else if (!etRegisterPassword.getText().toString().equals(passConfirm.getText().toString())) {
            etRegisterPassword.setError("Passwords are not equal");
            return false;
        }

        return true;
    }

    private boolean checkLoginFields(EditText etLoginEmail, EditText etLoginPassword) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

        if (etLoginEmail.length() == 0 || !etLoginEmail.getText().toString().matches(emailPattern)) {
            etLoginEmail.setError("Email is required");
            return false;
        }

        if (etLoginPassword.length() < 8) {
            etLoginPassword.setError("Password must be minimum 8 characters");
            return false;
        }

        return true;
    }

    //code check temporary hardcoded here. In future will be moved to server
    public void verifySentCode(EditText et_pv_code, UserAuthListener authListener) {

        String sentCode = getVerifCode();

        if (et_pv_code.getText().toString().equals(sentCode)) {

            if (isUserExists()) authListener.onLogin();
            else authListener.onRegister();

        } else {
            et_pv_code.getText().clear();
            et_pv_code.setError("Codes didn't match");
        }
    }

    String generateVerificationCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        String sentCode = String.format("%06d", number);

        Log.i("verifySentCode()", "sentCode: " + sentCode);

        return sentCode;
    }

    void changeVerificationViews(TextView tv_enter_phone, TextView tv_notification,
                                 EditText et_pv_phone, EditText et_pv_code,
                                 Button btn_phone_submit, Button btn_code_submit,
                                 String phone) {

        et_pv_phone.getText().clear();
        et_pv_phone.setHint("SMS code");

        tv_enter_phone.setText("Enter verification code that we sent to " + phone);
        btn_phone_submit.setVisibility(View.INVISIBLE);
        btn_code_submit.setVisibility(View.VISIBLE);
        et_pv_phone.setVisibility(View.INVISIBLE);
        tv_notification.setVisibility(View.VISIBLE);
        et_pv_code.setVisibility(View.VISIBLE);
    }

    public interface UserAuthListener {
        void onLogin();

        void onRegister();
    }

    public interface UserEnterListener {
        void onError(String msg);

        void onSuccess();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserExists() {
        return isUserExists;
    }

    public void setUserExists(boolean userExists) {
        isUserExists = userExists;
    }

    public String getVerifCode() {
        return verifCode;
    }

    public void setVerifCode(String verifCode) {
        this.verifCode = verifCode;
    }
}
