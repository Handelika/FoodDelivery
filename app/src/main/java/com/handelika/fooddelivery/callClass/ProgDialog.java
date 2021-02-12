package com.handelika.fooddelivery.callClass;

import android.app.ProgressDialog;
import android.content.Context;

import com.handelika.fooddelivery.R;

import java.util.Objects;


public class ProgDialog {

    //region callProgress dialog
    public static class DialogsUtils {
        public static ProgressDialog showProgressDialog(Context context, String message) {
            ProgressDialog m_Dialog = new ProgressDialog(context, R.style.Theme_MaterialComponents_DayNight_Dialog_Bridge);
            m_Dialog.setMessage(message);
            m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            Objects.requireNonNull(m_Dialog.getWindow()).setBackgroundDrawableResource(R.color.colorBeige);
            m_Dialog.setCancelable(false);
            m_Dialog.show();
            return m_Dialog;
        }
    }
    //endregion

}
