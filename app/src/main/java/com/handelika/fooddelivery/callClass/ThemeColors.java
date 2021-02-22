package com.handelika.fooddelivery.callClass;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class ThemeColors {

    public static int getThemeColor(Context context){
        return Color.parseColor( getShareDefaults("themeColor", context));
    }

    public static GradientDrawable gradientBackgroundTop(Context context) {

        int color = getThemeColor(context);

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f),gradientColor(color,0.6f)});
        gd.setCornerRadius(0f);

        return gd;
    }

    public static GradientDrawable gradientBackgroundBottom(Context context) {

        int color = getThemeColor(context);

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f),gradientColor(color,0.6f)});
        gd.setCornerRadius(0f);

        return gd;
    }

    public static int gradientColor(int color, float factor) {

        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);


        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }


}