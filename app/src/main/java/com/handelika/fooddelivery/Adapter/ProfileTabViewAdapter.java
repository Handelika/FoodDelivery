package com.handelika.fooddelivery.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.card.MaterialCardView;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.ThemeColors;

import java.util.ArrayList;
import java.util.List;

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

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public View getTabView(int position) {

        int color = ThemeColors.getThemeColor(context);

        View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);

        TextView txtTabTitle = tab.findViewById(R.id.txtTabTitle);
        MaterialCardView cvTab = tab.findViewById(R.id.cvTab);

        txtTabTitle.setTextColor(Color.WHITE);
        txtTabTitle.setText(mFragmentTitleList.get(position));
        return tab;
    }



}
