package com.example.carpoolbuddypro.audrey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.carpoolbuddypro.Myriam.UserProfileFragment;
import com.example.carpoolbuddypro.Myriam.YourVehicleFragment;
import com.example.carpoolbuddypro.R;
//import com.example.carpoolbuddypro.silvia.AvailableVehiclesFragment;
import com.example.carpoolbuddypro.silvia.AddVehicleFragment;
import com.example.carpoolbuddypro.silvia.ChatFragment;
import com.example.carpoolbuddypro.silvia.MapsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.common.collect.Maps;

public class TestingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        /*create toolbar and set for activity
          and set up layout of navigation bar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //set default fragment to be displayed when app is first opened
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new UserProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        //set the fragment displayed depending on which item is selected by user
        switch(item.getItemId())
        {
            case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserProfileFragment()).commit();
                    break;
            //your vehicles was not completed
            /*case R.id.nav_yourvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new YourVehicleFragment()).commit();
                break;*/
            case R.id.nav_newvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddVehicleFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                break;
            case R.id.nav_bookvehicles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AvailableVehiclesFragment()).commit();
                break;
            case R.id.maps:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapsFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        //close drawer if user clicks out of drawer
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            {
                super.onBackPressed();
            }
    }
}
