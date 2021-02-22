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


public class LoginActivity extends AppCompatActivity {

    private LinearLayout mainLinear;
    private MaterialButton btnLogin;
    private TextView txtRegister,txtUsername,txtPass,txtForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainLinear = findViewById(R.id.mainLinear);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtForgetPassword = findViewById(R.id.txtForgetPassword);

        hideSystemUI();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = TextClean.Temizle(txtUsername.getText().toString().trim());
                String password = TextClean.Temizle(txtPass.getText().toString().trim());

                if (username.length()>0 && password.length()>0){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finishAffinity();
                }else{

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(getApplicationContext(), "Lütfen kullanıcı adı ve şifre alanını doldurunuz!", getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();

                }


            }
        });

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

    public void txtRegisterClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}