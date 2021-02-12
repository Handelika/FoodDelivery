package com.handelika.fooddelivery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.handelika.fooddelivery.ui.CartFragment;
import com.handelika.fooddelivery.ui.MenuFragment;
import com.handelika.fooddelivery.ui.ProfileFragment;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView navView;
    private NavController navController;

    private Fragment selectedFragment;

    private int color;

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
        color = Color.parseColor( getShareDefaults("themeColor", getApplicationContext()));
        customizeTheme(color);
    }

    private void customizeTheme(int color) {

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
                        gradientColor(color, 0.8f),
                        gradientColor(color, 1f),
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
                        gradientColor(color, 0.8f),
                        gradientColor(color, 1f),
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
}