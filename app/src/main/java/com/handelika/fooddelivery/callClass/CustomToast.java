package com.handelika.fooddelivery.callClass;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.handelika.fooddelivery.R;

public class CustomToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */

    private Context context;
    private String message;
    private Drawable drawable;

    public CustomToast(Context context, String message, Drawable drawable) {
        super(context);

        int color = ThemeColors.getThemeColor(context);

        this.context = context;
        this.message = message;
        this.drawable = drawable;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);

        view.setLayoutParams(layoutParams);

        TextView txtMsg = view.findViewById(R.id.txt_message);
        CardView cvToast = view.findViewById(R.id.cvToast);
        ImageView imgToast = view.findViewById(R.id.imgToast);
        imgToast.setImageDrawable(drawable);
        cvToast.setCardBackgroundColor(color);
        txtMsg.setTextColor(Color.WHITE);
        txtMsg.setText(message);
        setView(view);
        setDuration(Toast.LENGTH_LONG);

    }
}
