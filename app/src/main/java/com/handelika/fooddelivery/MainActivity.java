package com.handelika.fooddelivery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.handelika.fooddelivery.callClass.ThemeColors;
import com.handelika.fooddelivery.ui.CartFragment;
import com.handelika.fooddelivery.ui.MenuFragment;
import com.handelika.fooddelivery.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView navView;
    private NavController navController;

    private Fragment selectedFragment;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener( this);


        BadgeDrawable badgeDrawable = navView.getBadge(R.id.navigation_cart);
        if (badgeDrawable == null)
        {
            navView.getOrCreateBadge(R.id.navigation_cart).setNumber(5);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_menu, R.id.navigation_cart, R.id.navigation_profile, R.id.navigation_add_address)
                .build();


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //theme customization
        customizeTheme();
    }

    private void customizeTheme() {

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        ThemeColors.gradientColor(ThemeColors.getThemeColor(getApplicationContext()), 0.8f),
                        ThemeColors.gradientColor(ThemeColors.getThemeColor(getApplicationContext()), 1f),
                        Color.WHITE,
                        Color.WHITE,
                        Color.WHITE,
                }
        );

        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        ThemeColors.gradientColor(ThemeColors.getThemeColor(getApplicationContext()), 0.8f),
                        ThemeColors.gradientColor(ThemeColors.getThemeColor(getApplicationContext()), 1f),
                        Color.WHITE,
                        Color.WHITE,
                        Color.WHITE,
                }
        );


        navView.setBackgroundColor(getResources().getColor(R.color.white));
        navView.setItemTextColor(navMenuTextList);
        navView.setItemIconTintList(navMenuIconList);

//        //changing status bar color
//        Window window = this.getWindow();
//        //Makes status bar transparent
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        // finally change the color
//        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparent));
        //window.setStatusBarColor(gradientColor(R.color.colorBlackBoard, 0.2f));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.navigation_menu:
                selectedFragment = new MenuFragment();
                goToFragment(selectedFragment);
                return true;

                case R.id.navigation_cart:
                selectedFragment = new CartFragment();
                goToFragment(selectedFragment);
                return true;

                  case R.id.navigation_profile:
                selectedFragment = new ProfileFragment();
                goToFragment(selectedFragment);
                return true;



        }


        return false;
    }

    //region goToFragment
    private void goToFragment(Fragment selectFragment) {

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    //endregion


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (count==2) {

            count=0;

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Çıkış");
            dialog.setCancelable(true);
            dialog.setMessage("Uygulamadan çıkmak istediğinize emin misiniz?");
            dialog.setNegativeButton("Hayır", null);
            dialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    count = 0;
                    finishAffinity();
                    System.exit(0);
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        }else{
            count++;
        }

    }
}