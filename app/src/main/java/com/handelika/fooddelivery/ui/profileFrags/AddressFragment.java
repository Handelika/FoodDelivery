package com.handelika.fooddelivery.ui.profileFrags;

import android.content.Context;
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
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.handelika.fooddelivery.Adapter.SliderAdapter;
import com.handelika.fooddelivery.Adapter.UserAddressAdapter;
import com.handelika.fooddelivery.Models.Slider;
import com.handelika.fooddelivery.Models.UserAddress;
import com.handelika.fooddelivery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment implements UserAddressAdapter.AddressClick{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private UserAddressAdapter userAddressAdapter;
    private List<UserAddress> userAddressList = new ArrayList<>();
    private RecyclerView rvAddress;

    private MaterialCardView cvAddAddress;

    private Fragment selected_fragment;

    private Context context;

    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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

        View view = inflater.inflate(R.layout.fragment_address, container, false);
        context = getContext();

        rvAddress = view.findViewById(R.id.rvAddress);
        cvAddAddress = view.findViewById(R.id.cvAddAddress);

        userAddressList.add(new UserAddress(1,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(2,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(3,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(4,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(5,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(6,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(7,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(8,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));
        userAddressList.add(new UserAddress(9,"Ev Adresi", "Bostanlı Mahallesi Cengiz Kocatoros Sok. Bostanlı Karşıyaka/İzmir"));

        runAnimation(rvAddress,userAddressList,0);

        cvAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_fragment = new AddAddressFragment();
                goToFragment(selected_fragment);
            }
        });

        return view;
    }

    //region runAnimation
    private void runAnimation(RecyclerView rvList, List<UserAddress> list, int type) {
        Context context = rvList.getContext();
        LayoutAnimationController controller = null;

        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.hasFixedSize();


            userAddressAdapter = new UserAddressAdapter(context,list, this::onAdressClick);
            rvList.setAdapter(userAddressAdapter);
            rvList.setLayoutAnimation(controller);
            rvList.scheduleLayoutAnimation();

        }
    }
    //endregion

    @Override
    public void onAdressClick(int index) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //region goToFragment
    public void goToFragment(Fragment selectFragment) {

        AppCompatActivity activity = (AppCompatActivity) getContext();

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectFragment).addToBackStack(null).commit();
    }
    //endregion
}