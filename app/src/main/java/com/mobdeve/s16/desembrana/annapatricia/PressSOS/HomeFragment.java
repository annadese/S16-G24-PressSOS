package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment{
    private static final String TAG = "HomeFragment1";
    private static final int INTERVAL = 5000;    // 5 minutes
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    private Button btnSOS;
    private Switch btnAlarm;
    private MediaPlayer alarmSound;
    private DbHelper helper;
    private ArrayList<Contact> contacts;
    //private FusedLocationProviderClient fusedLocationProviderClient;

    // Components related to the service
    private boolean bound;
    private LocationService locationService;
    private Intent locationIntent;

    private int isPressed = 0;

    Handler handler = new Handler();
    Runnable myRunnable = new Runnable() {
        public void run() {
            locationService.sendSOS();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        helper = new DbHelper(getContext());

        this.btnSOS = (Button)view.findViewById(R.id.main_btnsos);
        this.btnAlarm = (Switch)view.findViewById(R.id.main_btnalarm);

        // Saves a copy of the intent (for when we want to start or cancel the service again)
        this.locationIntent = new Intent(getActivity(), LocationService.class);

        // when SOS button is pressed
        this.btnSOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isPressed == 0) {
                    isPressed = 1;
                    locationService.alarmOn();
                    locationService.sendSOS();
                    handler.postDelayed(myRunnable, INTERVAL); //Every 120000 ms (2 minutes)

                } else {
                    isPressed = 0;
                    locationService.alarmOff();
                    handler.removeCallbacks(myRunnable);
                //    getActivity().unbindService(lConnection);
                    getActivity().stopService(locationIntent);

                }
            }
        });

        if(!bound) {
            getActivity().bindService(locationIntent, lConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(locationIntent);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if a service is bound. This will always be true given our current setup, but it
        // would be useful if this were to be modified to handle playing songs outside of the app.
        if(!bound) {
            getActivity().bindService(locationIntent, lConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(locationIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().stopService(locationIntent);
        getActivity().unbindService(lConnection);
    }

    // Logic for when LocationService is bound to the activity.
    private ServiceConnection lConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG,"onServiceConnected: ServiceConnection made");
            // Gets the binder
            LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            // Gets the service from the binder
            locationService = binder.getService();
            bound = true;

            // Passes the data to the LocationService
            locationService.setContacts(contacts);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG,"onServiceConnected: Disconnected made");
            bound = false;
            locationService = null;
        }
    };
}
