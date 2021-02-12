package com.handelika.fooddelivery.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.handelika.fooddelivery.Adapter.MenuAdapter;
import com.handelika.fooddelivery.Adapter.SliderAdapter;
import com.handelika.fooddelivery.Models.Menu;
import com.handelika.fooddelivery.R;

import java.util.ArrayList;
import java.util.List;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements SliderAdapter.SliderOnClick,MenuAdapter.MenuItemClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MenuAdapter menuAdapter;
    private List<Menu> menuList = new ArrayList<>();
    private RecyclerView rvMenu;

    private MaterialCardView cvProductHeader;

    private Context context;
    private View view;

    private ColorStateList checkColorStateList;

    private TextView txtStickyMenuHeader;

    private Fragment selectedFragment;

    private ImageView imgCart;

    BottomNavigationView navView;

    private RelativeLayout rlMenuDetail, relativeExtractedSupplies, relativeMeatballSelection,relativeCheddarSelection, relativeDrinkSelection;

    private  int color ;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        context = getContext();

        imgCart = view.findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new CartFragment();
                goToFragment(selectedFragment);
            }
        });

        //changing status bar color
        Window window = getActivity().getWindow();
        //Makes status bar transparent
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        cvProductHeader = view.findViewById(R.id.cvProductHeader);
        rvMenu = view.findViewById(R.id.rvMenu);
        txtStickyMenuHeader = view.findViewById(R.id.txtStickyMenuHeader);
        navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        menuList.add(new Menu(1,"Klasik Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "18.95₺", "hamburger"));

        menuList.add(new Menu(2,"Cheese Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "18.95₺", "hamburger"));

        menuList.add(new Menu(3,"Gurme Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "18.95₺", "hamburger"));

        menuList.add(new Menu(4,"Tavuk Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "34.95₺", "hamburger"));

        menuList.add(new Menu(5,"Tavuk Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "34.95₺", "hamburger"));

        menuList.add(new Menu(6,"Tavuk Burger","Hamburger sosu, cheddar peyniri, göbek marul, domates, ketçap,mayonez", "34.95₺", "hamburger"));


        runAnimationMenu(rvMenu,menuList,0);

        color = Color.parseColor( getShareDefaults("themeColor", context));
        customizeTheme(color);



        return view;
    }

    private void customizeTheme(int color) {

        //cvProductHeader.setCardBackgroundColor(color);

        checkColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},

                },
                new int[] {
                        color,
                        Color.GRAY,
                }
        );
    }


    //region runAnimationMenu
    private void runAnimationMenu(RecyclerView rvList, List<Menu> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.hasFixedSize();


            menuAdapter = new MenuAdapter(context,list, this::menuOnClick);
            rvList.setAdapter(menuAdapter);
            rvList.setLayoutAnimation(controller);
            rvList.scheduleLayoutAnimation();

        }
    }
    //endregion

    @Override
    public void sliderBtnOnClick(int index) {

    }

    @Override
    public void menuOnClick(int index) {

        getMenuDetails(menuList.get(index));

    }

    private void getMenuDetails(Menu menu) {

        ImageView imgMenu = view.findViewById(R.id.imgMenu);
        ImageView imgClose = view.findViewById(R.id.imgClose);
        TextView txtMenuHeader = view.findViewById(R.id.txtMenuHeader);
        TextView txtMenuDetail = view.findViewById(R.id.txtMenuDetail);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        MaterialButton btnAddToCart = view.findViewById(R.id.btnAddToCart);

        LinearLayout linearMenuDetail = view.findViewById(R.id.linearMenuDetail);

        //headers
        TextView txtExtractHeader = view.findViewById(R.id.txtExtractHeader);
        TextView txtMeatballHeader = view.findViewById(R.id.txtMeatballHeader);
        TextView txtCheddarHeader = view.findViewById(R.id.txtCheddarHeader);
        TextView txtDrinkHeader = view.findViewById(R.id.txtDrinkHeader);
        TextView txtAdditionalsHeader = view.findViewById(R.id.txtAdditionalsHeader);
        txtExtractHeader.setTextColor(color);
        txtMeatballHeader.setTextColor(color);
        txtCheddarHeader.setTextColor(color);
        txtDrinkHeader.setTextColor(color);
        txtAdditionalsHeader.setTextColor(color);
        btnAddToCart.setBackgroundColor(color);

        //Checkboxes
        MaterialCheckBox checkKetchup,checkMyoniese,checkGarlicSouce,checkBarbequeSouce,checkLemon;
        checkKetchup = view.findViewById(R.id.checkKetchup);
        checkMyoniese = view.findViewById(R.id.checkMyoniese);
        checkGarlicSouce = view.findViewById(R.id.checkGarlicSouce);
        checkBarbequeSouce = view.findViewById(R.id.checkBarbequeSouce);
        checkLemon = view.findViewById(R.id.checkLemon);

        checkKetchup.setButtonTintList(checkColorStateList);
        checkMyoniese.setButtonTintList(checkColorStateList);
        checkGarlicSouce.setButtonTintList(checkColorStateList);
        checkBarbequeSouce.setButtonTintList(checkColorStateList);
        checkLemon.setButtonTintList(checkColorStateList);

        rlMenuDetail = view.findViewById(R.id.rlMenuDetail);

        rlMenuDetail.setVisibility(View.VISIBLE);
        rlMenuDetail.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top));



        String uri = "@drawable/" + menu.getMenuImgUrl();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable res = context.getResources().getDrawable(imageResource);
        imgMenu.setImageDrawable(res);

        txtMenuHeader.setText(menu.getMenu());
        txtMenuDetail.setText(menu.getMenuDetail());
        txtPrice.setText(menu.getMenuPrice());


        @SuppressLint("UseCompatLoadingForDrawables") Drawable closeDrawable = context.getResources().getDrawable(R.drawable.ic_baseline_close_24);
        closeDrawable.setTint(color);
        imgClose.setImageDrawable(closeDrawable);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlMenuDetail.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fragment_fade_exit));
                rlMenuDetail.setVisibility(View.GONE);
            }
        });

        txtExtractHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractedSupplies();
            }
        });

        txtMeatballHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetBallSelection();
            }
        });

        txtCheddarHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheddarSelectin();
            }
        });

        txtDrinkHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drinkSelection();
            }
        });

    }

    private void drinkSelection() {
        relativeDrinkSelection = view.findViewById(R.id.relativeDrinkSelection);
        relativeDrinkSelection.setVisibility(View.VISIBLE);
        relativeDrinkSelection.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top));

        TextView txtOkDrink = view.findViewById(R.id.txtOkDrink);
        txtOkDrink.setTextColor(color);

        NumberPicker pickerDrink = view.findViewById(R.id.pickerDrink);

        String[] data = new String[]{"Yok", "Su", "Ayran", "Kola", "Gazoz", "Meyve Suyu", "Maden Suyu", };

        pickerDrink.setMinValue(0);
        pickerDrink.setMaxValue(data.length-1);
        pickerDrink.setDisplayedValues(data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            pickerDrink.setTextColor(color);
        }

        txtOkDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeDrinkSelection.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fragment_fade_exit));
                relativeDrinkSelection.setVisibility(View.GONE);
            }
        });
    }

    private void cheddarSelectin() {
        relativeCheddarSelection = view.findViewById(R.id.relativeCheddarSelection);
        relativeCheddarSelection.setVisibility(View.VISIBLE);
        relativeCheddarSelection.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top));

        TextView txtOkCheddar = view.findViewById(R.id.txtOkCheddar);
        txtOkCheddar.setTextColor(color);

        NumberPicker pickerCheddar = view.findViewById(R.id.pickerCheddar);

        String[] data = new String[]{"Yok", "Var(+3,00₺)"};

        pickerCheddar.setMinValue(0);
        pickerCheddar.setMaxValue(data.length-1);
        pickerCheddar.setDisplayedValues(data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            pickerCheddar.setTextColor(color);
        }

        txtOkCheddar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeCheddarSelection.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fragment_fade_exit));
                relativeCheddarSelection.setVisibility(View.GONE);
            }
        });
    }

    private void extractedSupplies() {

        TextView txtOk = view.findViewById(R.id.txtOk);
        relativeExtractedSupplies = view.findViewById(R.id.relativeExtractedSupplies);
        relativeExtractedSupplies.setVisibility(View.VISIBLE);
        relativeExtractedSupplies.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top));


        MaterialCheckBox checkKetchupExtract, checkMayonieseExtract,checkLettuceExtract,checkBarbequeExtract,checkOnionRingsExtract;

        checkKetchupExtract = view.findViewById(R.id.checkKetchupExtract);
        checkMayonieseExtract = view.findViewById(R.id.checkMayonieseExtract);
        checkLettuceExtract = view.findViewById(R.id.checkLettuceExtract);
        checkBarbequeExtract = view.findViewById(R.id.checkBarbequeExtract);
        checkOnionRingsExtract = view.findViewById(R.id.checkOnionRingsExtract);

        checkKetchupExtract.setButtonTintList(checkColorStateList);
        checkMayonieseExtract.setButtonTintList(checkColorStateList);
        checkLettuceExtract.setButtonTintList(checkColorStateList);
        checkBarbequeExtract.setButtonTintList(checkColorStateList);
        checkOnionRingsExtract.setButtonTintList(checkColorStateList);
        txtOk.setTextColor(color);

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeExtractedSupplies.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fragment_fade_exit));
                relativeExtractedSupplies.setVisibility(View.GONE);
            }
        });

    }

    private void meetBallSelection(){

        relativeMeatballSelection = view.findViewById(R.id.relativeMeetballSelection);
        relativeMeatballSelection.setVisibility(View.VISIBLE);
        relativeMeatballSelection.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top));

        TextView txtOkMeetBall = view.findViewById(R.id.txtOkMeetBall);
        txtOkMeetBall.setTextColor(color);

        NumberPicker pickerMeetball = view.findViewById(R.id.pickerMeetball);

        String[] data = new String[]{"Tek Köfte", "Çift Köfte(+10,00₺)", "Triple Köfte(+15,00₺)"};
        pickerMeetball.setMinValue(0);
        pickerMeetball.setMaxValue(data.length-1);
        pickerMeetball.setDisplayedValues(data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            pickerMeetball.setTextColor(color);
        }

        txtOkMeetBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeMeatballSelection.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fragment_fade_exit));
                relativeMeatballSelection.setVisibility(View.GONE);
            }
        });

    }

    //region goToFragment
    public void goToFragment(Fragment selectFragment) {

        AppCompatActivity activity = (AppCompatActivity) getContext();

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectFragment).addToBackStack(null).commit();
    }
    //endregion

}