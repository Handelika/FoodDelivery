package com.handelika.fooddelivery.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.handelika.fooddelivery.Adapter.ProfileTabViewAdapter;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.ThemeColors;
import com.handelika.fooddelivery.ui.profileFrags.AddressFragment;
import com.handelika.fooddelivery.ui.profileFrags.PreviousOrdersFragment;

import static com.handelika.fooddelivery.callClass.ThemeColors.gradientColor;

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
        customizeTheme();


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

    private void customizeTheme() {

        int color = ThemeColors.getThemeColor(context);

        tabLayout.setTabTextColors(gradientColor(color, 1f), Color.WHITE);
        //tabLayout.setBackground(gradientBackground(color));
        txtEditProfile.setTextColor(color);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorGrayLight));
//        tabLayout.setElevation(6);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setBackground(ThemeColors.gradientBackgroundBottom(context));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                tab.view.setBackgroundColor(getResources().getColor(R.color.colorGrayLight));
                tabLayout.setTabTextColors(gradientColor(color, 1f), Color.BLACK);


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.view.setBackground(ThemeColors.gradientBackgroundBottom(context));
            }
        });

        tabLayout.setSelectedTabIndicatorColor(gradientColor(color,0.2f));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}