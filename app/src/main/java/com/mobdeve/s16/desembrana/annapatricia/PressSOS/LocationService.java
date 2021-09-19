package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LocationService extends Service {

    private final String TAG = "LocationService1";

    private int currLocationPosition = -1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private MediaPlayer alarmSound;
    private Switch btnAlarm;

    // Constants for intent actions
    public static final String
            PLAY_ACTION = "start_action",
            PAUSE_ACTION = "pause_action",
            STOP_ACTION = "stop_action";

    // Constants for intent keys
    public static final String
            NUMBER_INTENT_KEY = "NUMBER_INTENT_KEY",
            LATITUDE_INTENT_KEY = "LATITUDE_INTENT_KEY",
            LONGITUDE_INTENT_KEY = "LONGITUDE_INTENT_KEY",
            DATE_INTENT_KEY = "DATE_INTENT_KEY";

    // data
    private ArrayList<Llocation> locations;
    private ArrayList<Contact> contacts;
    private DbHelper helper;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG,"onStartCommand has been called");

        this.alarmSound = MediaPlayer.create(this, R.raw.alarm);
        // this.btnAlarm = (Switch) this.findViewById(R.id.main_btnalarm);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        helper = new DbHelper(this);

        return Service.START_STICKY;
    }

    // gets the current location of the user and sends SOS messages to contacts
    public void sendSOS() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //get location
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            String lat = location.getLatitude() + "";
                            String longt = location.getLongitude() + "";

                            SimpleDateFormat dFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                            Date date = new Date(new Date().getTime());
                            String d = dFormat.format(date);

                            helper.insertLocation(new Llocation(lat, longt, d));

                            sendTextMessages(lat, longt);
                        }
                    }
                });
            } else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sendTextMessages (String lat, String longt) {
        SmsManager smsManager = SmsManager.getDefault();
        SharedPreferences sp = this.getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        contacts = helper.getAllContactsDefault();

        String sos = sp.getString(Keys.SOS_MESSAGE_KEY.name(), "SOS Message");

        String link = "google.com/maps/dir/?api=1&destination=" + lat + "," + longt;
        sos = sos + link;

        // send text to all emergency contacts
        for (int i = 0; i < contacts.size(); i++) {
            smsManager.sendTextMessage(contacts.get(i).getContactNumber(), null, sos, null, null);
        }

        Toast.makeText(this, "SOS sent", Toast.LENGTH_LONG).show();
    }

    public void setContacts( ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    // method for turning on alarm sound
    public void alarmOn(){
        alarmSound.setLooping(true);
        alarmSound.start();
    }

    // method for turning off alarm sound
    public void alarmOff(){
        alarmSound.pause();
        alarmSound.seekTo(0);
    }

    // -----Start of Service Binding Components-----
    private final IBinder binder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }
    // -----End of Service Binding Components-----
}
