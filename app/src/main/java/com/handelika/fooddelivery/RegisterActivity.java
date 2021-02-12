package com.handelika.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class RegisterActivity extends AppCompatActivity {

    private LinearLayout mainLinear;
    private TextView txtLogin;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mainLinear = findViewById(R.id.mainLinear);
        txtLogin = findViewById(R.id.txtLogin);

        hideSystemUI();

        color = Color.parseColor( getShareDefaults("themeColor", getApplicationContext()));
        mainLinear.setBackground(gradientBackgroundBottom(color));

    }

    private GradientDrawable gradientBackgroundTop(int color) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f),gradientColor(color,0.6f)});
        gd.setCornerRadius(0f);

        return gd;
    }

    private GradientDrawable gradientBackgroundBottom(int color) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f),gradientColor(color,0.6f)});
        gd.setCornerRadius(0f);

        return gd;
    }

    private int gradientColor(int color, float factor) {

        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);


        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
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
}