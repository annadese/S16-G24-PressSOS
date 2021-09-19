package com.mobdeve.s16.desembrana.annapatricia.PressSOS;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private static final String TAG = "HomeFragment1";
    private static final int INTERVAL = (1000 * 60) * 5;    // 5 minutes

    private DbHelper helper;
    private ArrayList<Contact> contacts;

    // Components related to the service
    private boolean bound;
    private LocationService locationService;
    private Intent locationIntent;

    // Components for alarm
    private Button btnSOS;
    private Switch btnAlarm;
    private int isPressed = 0;

    // Components for Handler
    Handler handler = new Handler();
    Runnable myRunnable = new Runnable() {
        public void run() {

            locationService.sendSOS();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // initializes DBhelper and contacts
        helper = new DbHelper(getContext());
        contacts = helper.getAllContactsDefault();

        // initializes button and switch
        this.btnSOS = (Button) view.findViewById(R.id.main_btnsos);
        this.btnAlarm = (Switch) view.findViewById(R.id.main_btnalarm);

        // saves a copy of the intent
        this.locationIntent = new Intent(getActivity(), LocationService.class);

        // when SOS button is pressed
        this.btnSOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // turn on SOS
                if (isPressed == 0) {
                    isPressed = 1;
                    locationService.sendSOS();
                    handler.postDelayed(myRunnable, INTERVAL);

                    btnSOS.setBackgroundTintList(getResources().getColorStateList(R.color.inner_brown));

                    if (btnAlarm.isChecked()) {
                        locationService.alarmOn();
                    }

                // turn off SOS
                } else {
                    isPressed = 0;
                    locationService.alarmOff();
                    handler.removeCallbacks(myRunnable);
                    getActivity().stopService(locationIntent);

                    btnSOS.setBackgroundTintList(getResources().getColorStateList(R.color.dark_red));
                }
            }
        });

        if(!bound) {
            getActivity().bindService(locationIntent, lConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(locationIntent);
        }
        return view;
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

    @Override
    public void onStart() {
        super.onStart();

        if(!bound) {
            getActivity().bindService(locationIntent, lConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(locationIntent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        this.isPressed = isPressed;
    }
}
