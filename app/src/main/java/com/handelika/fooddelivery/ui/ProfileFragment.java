package com.handelika.fooddelivery.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.handelika.fooddelivery.Adapter.ProfileTabViewAdapter;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.ui.profileFrags.AddressFragment;
import com.handelika.fooddelivery.ui.profileFrags.PreviousOrdersFragment;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    private ProfileTabViewAdapter adapter;

    private int color;

    private TextView txtEditProfile;

    BottomNavigationView navView;

    private Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getContext();

        navView =  getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        txtEditProfile =  view.findViewById(R.id.txtEditProfile);
        tabLayout =  view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.pager);

        //theme customization
        color = Color.parseColor( getShareDefaults("themeColor", context));
        customizeTheme(color);


        getTabs();



        return view;
    }

    private void getTabs() {
        adapter = new ProfileTabViewAdapter(getChildFragmentManager(),context);
        tabLayout.setupWithViewPager(viewPager);

        // add your fragments
        adapter.addFrag(new AddressFragment(), "Adreslerim");
        adapter.addFrag(new PreviousOrdersFragment(), "Önceki Siparişlerim");

        // set adapter on viewpager
        viewPager.setAdapter(adapter);

//        custom tab view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();


       getTabs();


    }

    private void customizeTheme(int color) {

        tabLayout.setTabTextColors(gradientColor(color, 1f), Color.WHITE);
        //tabLayout.setBackground(gradientBackground(color));
        txtEditProfile.setTextColor(color);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorGrayLight));
//        tabLayout.setElevation(6);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setBackground(gradientBackground(color));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                tab.view.setBackgroundColor(getResources().getColor(R.color.colorGrayLight));
                tabLayout.setTabTextColors(gradientColor(color, 1f), Color.BLACK);


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.view.setBackground(gradientBackground(color));
            }
        });

        tabLayout.setSelectedTabIndicatorColor(gradientColor(color,0.2f));
    }

    //region gradientColor
    private GradientDrawable gradientBackground(int color) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f)});
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


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}