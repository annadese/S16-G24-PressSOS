package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

//    public static final int

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar);

        this.bottomNav = findViewById(R.id.bottomNavigationView);

        this.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.info_item:
                        selectedFragment = new InfoFragment();
                        break;
                    case R.id.locationHistory_item:
                        selectedFragment = new LocationHistoryFragment();
                        break;
                    case R.id.home_item:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.emergencyContacts_item:
                        selectedFragment = new EmergencyContactsFragment();
                        break;
                    case R.id.account_item:
                        selectedFragment = new AccountFragment();
                        break;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();

                return true;
            }
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.home_item); // change to whichever id should be default
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }


}