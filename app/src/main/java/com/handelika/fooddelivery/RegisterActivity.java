package com.handelika.fooddelivery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.handelika.fooddelivery.callClass.CustomToast;
import com.handelika.fooddelivery.callClass.TextClean;
import com.handelika.fooddelivery.callClass.ThemeColors;

public class RegisterActivity extends AppCompatActivity {

    private LinearLayout mainLinear;
    private TextView txtLogin, txtUsername, txtPass, txtPassVerify;
    private MaterialButton btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mainLinear = findViewById(R.id.mainLinear);
        txtLogin = findViewById(R.id.txtLogin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtPassVerify = findViewById(R.id.txtPassVerify);
        btnRegister = findViewById(R.id.btnRegister);

        hideSystemUI();

        mainLinear.setBackground(ThemeColors.gradientBackgroundBottom(getApplicationContext()));

    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        // remove the following flag for version < API 19
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

    public void txtLoginClick(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    public void btnRegisterClick(View view) {

        String username = TextClean.Temizle(txtUsername.getText().toString().trim());
        String password = TextClean.Temizle(txtPass.getText().toString().trim());
        String passwordVerify = TextClean.Temizle(txtPassVerify.getText().toString().trim());

        if (
                username.length()>0 &&
                password.length()>0 &&
                passwordVerify.length()>0
        ){

            if (password.equals(passwordVerify)) {

                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finishAffinity();

            }else{
                @SuppressLint("UseCompatLoadingForDrawables")
                CustomToast toast = new CustomToast(getApplicationContext(), "Lütfen girdiğiniz şifrelerin aynı olduğundan emin olunuz!", getResources().getDrawable(R.drawable.ic_baseline_info_24));
                toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                toast.show();
            }
        }else{

            @SuppressLint("UseCompatLoadingForDrawables")
            CustomToast toast = new CustomToast(getApplicationContext(), "Lütfen kullanıcı adı ve şifre alanını doldurunuz!", getResources().getDrawable(R.drawable.ic_baseline_info_24));
            toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
            toast.show();

        }

    }
}