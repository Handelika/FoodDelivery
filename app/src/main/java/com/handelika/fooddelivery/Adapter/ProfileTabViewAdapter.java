package com.handelika.fooddelivery.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.card.MaterialCardView;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.ui.profileFrags.AddressFragment;
import com.handelika.fooddelivery.ui.profileFrags.PreviousOrdersFragment;

import java.util.ArrayList;
import java.util.List;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class ProfileTabViewAdapter extends FragmentPagerAdapter {


    private Context context;

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ProfileTabViewAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mFragmentTitleList.get(position);
    }

//    public void addFrag(Fragment fragment) {
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add("");
//    }


    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public View getTabView(int position) {

        int color = Color.parseColor( getShareDefaults("themeColor", context));

        View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);

        TextView txtTabTitle = tab.findViewById(R.id.txtTabTitle);
        MaterialCardView cvTab = tab.findViewById(R.id.cvTab);

        txtTabTitle.setTextColor(Color.WHITE);
        txtTabTitle.setText(mFragmentTitleList.get(position));
        return tab;
    }

    //region gradientColor
    private GradientDrawable gradientBackground(int color) {
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
    //endregion

}
