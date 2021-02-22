package com.handelika.fooddelivery.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.handelika.fooddelivery.Adapter.SliderAdapter;
import com.handelika.fooddelivery.Models.Slider;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.ThemeColors;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirmFragment extends Fragment implements SliderAdapter.SliderOnClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MaterialToolbar firmToolbar;

    private Context context;

    private AppBarLayout appBarLayout;

    private RecyclerView rvImgGallery;
    private SliderAdapter sliderAdapter;
    private List<Slider> sliderList = new ArrayList<>();

    public FirmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirmFragment newInstance(String param1, String param2) {
        FirmFragment fragment = new FirmFragment();
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

        View view = inflater.inflate(R.layout.fragment_firm, container, false);

        context = getContext();
        firmToolbar = view.findViewById(R.id.firmToolbar);
        rvImgGallery = view.findViewById(R.id.rvImgGallery);


        customizeTheme();

        sliderList.add(new Slider(1,"","duyuru",""));
        sliderList.add(new Slider(2,"","migros",""));

        runAnimationMenu(rvImgGallery,sliderList,0);

        return view;
    }

    //region runAnimationSlide
    private void runAnimationMenu(RecyclerView rvList, List<Slider> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.hasFixedSize();


            sliderAdapter = new SliderAdapter(context,list, this::sliderBtnOnClick);
            rvList.setAdapter(sliderAdapter);
            rvList.setLayoutAnimation(controller);
            rvList.scheduleLayoutAnimation();

        }
    }
    //endregion

    private void customizeTheme() {
        int color = ThemeColors.getThemeColor(context);

        firmToolbar.setBackground(gradientBackgroundTop(color));
//        firmToolbar.setBackground(getResources().getDrawable(R.drawable.food));
    }

    //region gradientColor
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
    //endregion

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void sliderBtnOnClick(int index) {

    }
}