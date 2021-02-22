package com.handelika.fooddelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static com.handelika.fooddelivery.callClass.SharePrefCall.setShareDefaults;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String colorStr = "#497c91";

        setShareDefaults("themeColor", colorStr, getApplicationContext());

        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

    }
}