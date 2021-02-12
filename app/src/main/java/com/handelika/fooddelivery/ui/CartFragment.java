package com.handelika.fooddelivery.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.handelika.fooddelivery.Adapter.CartItemsAdapter;
import com.handelika.fooddelivery.Adapter.UserAddressAdapter;
import com.handelika.fooddelivery.Models.CartItems;
import com.handelika.fooddelivery.Models.UserAddress;
import com.handelika.fooddelivery.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartItemsAdapter.CartItemsClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;

    private RecyclerView rvCartItems;
    private CartItemsAdapter cartItemsAdapter;
    private List<CartItems> cartItemsList = new ArrayList<>();

    private int color;

    BottomNavigationView navView;

    private Fragment selectedFragment;

    private TextView txtClose;
    private MaterialButton btnFinishOrder;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        context = getContext();
        color= Color.parseColor( getShareDefaults("themeColor", context));;

        txtClose = view.findViewById(R.id.txtClose);
        btnFinishOrder = view.findViewById(R.id.btnFinishOrder);
        rvCartItems = view.findViewById(R.id.rvCartItems);

        navView =  getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);

        customizeTheme();

        List<String> stringList = new ArrayList<>();
        stringList.add("- Ekstra Peynir");
        stringList.add("- Ekstra Soğan Halkası");
        stringList.add("- Mayonez Olmasın");

        for (int i = 0; i < 7; i++) {
            cartItemsList.add(new CartItems(i,1, "Klasik Burger", "hamburger", 2,"25.99₺", stringList));
        }

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new MenuFragment();
                goToFragment(selectedFragment);
            }
        });


        runAnimation(rvCartItems,cartItemsList,0);

        return view;
    }

    private void customizeTheme() {
        txtClose.setTextColor(color);
        btnFinishOrder.setBackgroundColor(color);
    }

    //region runAnimation
    private void runAnimation(RecyclerView rvList, List<CartItems> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.hasFixedSize();


            cartItemsAdapter = new CartItemsAdapter(context,list, this::onCartItemClick);
            rvList.setAdapter(cartItemsAdapter);
            rvList.setLayoutAnimation(controller);
            rvList.scheduleLayoutAnimation();

        }
    }
    //endregion

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCartItemClick(int index) {

    }

    //region goToFragment
    public void goToFragment(Fragment selectFragment) {

        AppCompatActivity activity = (AppCompatActivity) getContext();

        activity
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, selectFragment)
                .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit )
                .addToBackStack(null)
                .commit();
    }
    //endregion
}