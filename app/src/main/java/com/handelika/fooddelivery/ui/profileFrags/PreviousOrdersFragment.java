package com.handelika.fooddelivery.ui.profileFrags;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.handelika.fooddelivery.Adapter.OrdersAdapter;
import com.handelika.fooddelivery.Adapter.UserAddressAdapter;
import com.handelika.fooddelivery.Models.Orders;
import com.handelika.fooddelivery.Models.UserAddress;
import com.handelika.fooddelivery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousOrdersFragment extends Fragment implements OrdersAdapter.OrdersClickItem {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OrdersAdapter ordersAdapter;
    private List<Orders> ordersList = new ArrayList<>();
    private RecyclerView rvOrders;

    private Context context;

    public PreviousOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviousOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviousOrdersFragment newInstance(String param1, String param2) {
        PreviousOrdersFragment fragment = new PreviousOrdersFragment();
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
        View view = inflater.inflate(R.layout.fragment_previous_orders, container, false);
        rvOrders = view.findViewById(R.id.rvOrders);

        context = getContext();

        ordersList.add(new Orders(1,1,"01.02.2021","15:15", "2x Hamburger\n2x Hambburger", 2 , "18.25"));

        ordersList.add(new Orders(2,1,"01.02.2021","15:15", "2x Hamburger\n2x Hamburger\n2x Hamburger\n2x Hamburger\n2x Hamburger", 2 , "18.25"));

        ordersList.add(new Orders(3,1,"01.02.2021","15:15", "2x Hamburger", 2 , "18.25"));

        runAnimation(rvOrders,ordersList,0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        runAnimation(rvOrders,ordersList,0);

    }

    //region runAnimation
    private void runAnimation(RecyclerView rvList, List<Orders> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.hasFixedSize();


            ordersAdapter = new OrdersAdapter(context,list, this::ordersOnClick);
            rvList.setAdapter(ordersAdapter);
            rvList.setLayoutAnimation(controller);
            rvList.scheduleLayoutAnimation();

        }
    }
    //endregion

    @Override
    public void ordersOnClick(int index) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}