package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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

    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar);

        // check if the permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            //if permission is not granted, then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else { // a pop-up will appear asking for the required permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // will check the requestCode
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                // check whether the length of the grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}