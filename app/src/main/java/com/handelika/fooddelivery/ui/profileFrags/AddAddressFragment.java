package com.handelika.fooddelivery.ui.profileFrags;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.CustomToast;
import com.handelika.fooddelivery.callClass.TextClean;
import com.handelika.fooddelivery.callClass.ThemeColors;
import com.handelika.fooddelivery.ui.ProfileFragment;

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

    private EditText txtName,txtSurname,txtPhone,txtAddressHeadName,txtAddress,txtCity,txtState;

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

        declareNames(view);
        customizeTheme();


        btnSaveAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = TextClean.Temizle( txtName.getText().toString());
                String surname = TextClean.Temizle( txtSurname.getText().toString());
                String phone = TextClean.Temizle( txtPhone.getText().toString());
                String addressHead = TextClean.Temizle( txtAddressHeadName.getText().toString());
                String address = TextClean.Temizle( txtAddress.getText().toString());
                String city = TextClean.Temizle( txtCity.getText().toString());
                String state = TextClean.Temizle( txtState.getText().toString());

                if (name.length() < 3){
                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen ad alanını doldurunuz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                  toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                  toast.show();

                }else if (surname.length() < 3){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen soyad alanını doldurunuz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();

                }else if (phone.length() < 10){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen geçerli bir telefon numarası giriniz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();


                }else if (addressHead.length()<3){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen adres başlığını alanını doldurunuz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();


                }else if (address.length()< 3){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen adres alanını doldurunuz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();

                }else if (city.length() <3){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen şehir alanını doldurunuz!", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();


                }else if (state.length()<3){

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Lütfen ilçe alanını doldurunuz", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();


                }else{

                    @SuppressLint("UseCompatLoadingForDrawables")
                    CustomToast toast = new CustomToast(context, "Başarılı", context.getResources().getDrawable(R.drawable.ic_baseline_info_24));
                    toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
                    toast.show();


                }

            }
        });




        //fixing back button to return profile fragment
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

    private void customizeTheme() {
        btnSaveAdress.setBackgroundColor(ThemeColors.getThemeColor(context));

    }

    private void declareNames(View view) {
        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurname);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddressHeadName = view.findViewById(R.id.txtAddressHeadName);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtCity = view.findViewById(R.id.txtCity);
        txtState = view.findViewById(R.id.txtState);
        btnSaveAdress = view.findViewById(R.id.btnSaveAdress);

        //mainActivity navView
        navView =  getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);

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