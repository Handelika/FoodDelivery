package com.handelika.fooddelivery.ui.profileFrags;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.ui.ProfileFragment;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAddressFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Fragment selectedFragment;

    private MaterialButton btnSaveAdress;

    BottomNavigationView navView;

    private int color;
    private Context context;

    public AddAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAddressFragment newInstance(String param1, String param2) {
        AddAddressFragment fragment = new AddAddressFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        context = getContext();

        color = Color.parseColor( getShareDefaults("themeColor", context));

        navView =  getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);

        btnSaveAdress = view.findViewById(R.id.btnSaveAdress);
        btnSaveAdress.setBackgroundColor(color);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                selectedFragment = new ProfileFragment();
                goToFragment(selectedFragment);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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