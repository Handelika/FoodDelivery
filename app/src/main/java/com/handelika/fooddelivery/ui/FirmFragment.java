package com.handelika.fooddelivery.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.handelika.fooddelivery.Adapter.SliderAdapter;
import com.handelika.fooddelivery.Models.Slider;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.ExpandableTextView;
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
    private LinearLayout linearFirm;

    private AppBarLayout appBarLayout;

    private LinearLayout linearFirmInfo;

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
        linearFirm = view.findViewById(R.id.linearFirm);
        linearFirmInfo = view.findViewById(R.id.linearFirmInfo);

        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In dapibus, quam eu commodo venenatis, arcu mauris efficitur lorem, non egestas urna nisi quis mauris. Vestibulum imperdiet, libero ut ullamcorper faucibus, dolor dui venenatis lorem, a malesuada arcu nulla non arcu.";

        ExpandableTextView expandableTextView = new ExpandableTextView(context);
        expandableTextView.setText(text);
        expandableTextView.setEllipsize(TextUtils.TruncateAt.END);
        expandableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(15,5,15,5);

       linearFirmInfo.addView(expandableTextView);


        customizeTheme();

        sliderList.add(new Slider(1,"","hamburger",""));
        sliderList.add(new Slider(2,"","hamburger",""));
        sliderList.add(new Slider(3,"","hamburger",""));
        sliderList.add(new Slider(4,"","hamburger",""));
        sliderList.add(new Slider(5,"","hamburger",""));
        sliderList.add(new Slider(6,"","hamburger",""));
        sliderList.add(new Slider(7,"","hamburger",""));

        runAnimationMenu(rvImgGallery,sliderList,0);


        return view;
    }

    //region runAnimationSlide
    private void runAnimationMenu(RecyclerView rvList, List<Slider> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2,RecyclerView.VERTICAL,false);

            rvList.setLayoutManager(gridLayoutManager);
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

        linearFirm.setBackground(getResources().getDrawable(R.drawable.background1));
//        linearFirm.setBackground(ThemeColors.gradientBackgroundTop(context));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void sliderBtnOnClick(int index) {

    }



}