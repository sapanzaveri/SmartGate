package com.example.smartgate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgate.Fragment.ComplaintFragment;
import com.example.smartgate.Fragment.EventFragment;
import com.example.smartgate.Fragment.HelpDeskFragment;
import com.example.smartgate.Fragment.HomeFragment;
import com.example.smartgate.Fragment.NoticeFragment;
import com.example.smartgate.Fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout nav_drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    Fragment fragment = null;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        init();
        navHeader();
        if (savedInstanceState == null) {
            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,
                            new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportActionBar().setTitle("Home");
                fragment = new HomeFragment();

                navigationView.setCheckedItem(R.id.nav_home);
                break;
            case R.id.nav_helpdesk:
                getSupportActionBar().setTitle("Help Desk");
                fragment = new HelpDeskFragment();
                break;
            case R.id.nav_notice:
                getSupportActionBar().setTitle("Notice");
                fragment = new NoticeFragment();
                break;
            case R.id.nav_event:
                getSupportActionBar().setTitle("Event");
                fragment = new EventFragment();
                break;
            case R.id.nav_complaint:

                getSupportActionBar().setTitle("Complaint");
                fragment = new ComplaintFragment();
                break;
            case R.id.nav_logout:
                logoutUser();
                break;
        }

        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.fragment_container,
//                            fragment);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            for (int i=0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i){
                getSupportFragmentManager().popBackStackImmediate();

            }
            ft.commit();
//            getSupportFragmentManager().beginTransaction().commit();

        }

        nav_drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// User Logout
    private void logoutUser() {
        sharedPrefManager.logout();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "You have benn Logout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (nav_drawer.isDrawerOpen(GravityCompat.START)) {
            nav_drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
            }
        }


//    public void showHome() {
//        fragment = new HomeFragment();
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.fragment_container,
//                            fragment).commit();
//        }
//    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav_drawer = findViewById(R.id.nav_draw_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, nav_drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        nav_drawer.addDrawerListener(toggle);

        toggle.syncState();


    }

    public void navHeader() {
        View headerView = navigationView.getHeaderView(0);
        TextView textView = headerView.findViewById(R.id.txt_name);
        ImageView imageView = headerView.findViewById(R.id.img_profile);

        textView.setText(sharedPrefManager.getUser().getName());


        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment()).commit();
                nav_drawer.closeDrawer(GravityCompat.START);
                navigationView.setCheckedItem(R.id.nav_menu_none);
                getSupportActionBar().setTitle("Profile");


            }
        });

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void setNav(String title) {
        if (title.equalsIgnoreCase("Home")) {
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (title.equalsIgnoreCase("Notice")) {
            navigationView.setCheckedItem(R.id.nav_notice);

        }
    }
}