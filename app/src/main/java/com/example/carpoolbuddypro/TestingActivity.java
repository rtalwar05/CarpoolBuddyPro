package com.example.carpoolbuddypro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.carpoolbuddypro.Myriam.NewVehicleFragment;
import com.example.carpoolbuddypro.Myriam.Profile;
import com.example.carpoolbuddypro.Myriam.YourVehicleFragment;
import com.example.carpoolbuddypro.audrey.AvailableVehiclesFragment;
//import com.example.carpoolbuddypro.silvia.AvailableVehiclesFragment;
import com.example.carpoolbuddypro.silvia.ChatFragment;
import com.google.android.material.navigation.NavigationView;

public class TestingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //implements NavigationView.OnNavigationItemSelectedListener

    private DrawerLayout drawer;

    Button fragmentOneButton;
    Button fragmentTwoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
            navigationView.setCheckedItem(R.id.profile);
        }*/

        /*fragmentOneButton = findViewById(R.id.fragmentOneButton);
        fragmentTwoButton = findViewById(R.id.fragmentTwoButton);


        fragmentOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FragmentOne());
            }
        });

        fragmentTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FragmentTwo());
            }
        });*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_profile: {
                System.out.println("hey");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Profile()).commit();
                break;
            }
            case R.id.nav_yourvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new YourVehicleFragment()).commit();
                break;
            case R.id.nav_newvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewVehicleFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                break;
            case R.id.nav_bookvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AvailableVehiclesFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("profile click");
        switch(item.getItemId())
        {
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
                break;
            case R.id.yourvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new YourVehicleFragment()).commit();
                break;
            case R.id.newvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddVehicleFragment()).commit();
                break;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();
                break;
            case R.id.bookvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AvailableVehiclesFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            {
            super.onBackPressed();
        }
    }

    /*@Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }*/


}
