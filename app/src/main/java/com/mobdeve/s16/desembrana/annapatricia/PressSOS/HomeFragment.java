package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private Button btnSOS;
    private Switch btnAlarm;
    private MediaPlayer alarmSound;
    private DbHelper helper;
    ArrayList<Contact> contacts;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        helper = new DbHelper(getContext());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        this.alarmSound = MediaPlayer.create(getActivity(), R.raw.alarm);
        this.btnSOS = (Button)view.findViewById(R.id.main_btnsos);
        this.btnAlarm = (Switch)view.findViewById(R.id.main_btnalarm);

        this.btnSOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alarm();            // alarm sound is activated
                getLocation();
        //        sendTextMessage();  // SOS message is sent to all emergency contacts
            }
        });
        return view;
    }

    private void getLocation() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED){
                //get location
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Double lat = location.getLatitude();
                            Double longt = location.getLongitude();

                            //textView.setText(lat+ " , " + longt);
                            Toast.makeText(getActivity(), lat + ", " + longt, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }


    // method for the alarm sound
    public void alarm(){
        if (alarmSound.isPlaying()) { // alarm sound stops if the button is clicked while the alarm is playing
            alarmSound.pause();
            alarmSound.seekTo(0);
        } else if(btnAlarm.isChecked()){ // alarm sound starts if the button is clicked while the alarm is off.
            alarmSound.setLooping(true);
            alarmSound.start();
        }
    }

    // method for sending SOS message to all emergency contacts
    protected void sendTextMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        SharedPreferences sp = this.getContext().getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        contacts = helper.getAllContactsDefault();

        String sos = sp.getString(Keys.SOS_MESSAGE_KEY.name(), "SOS Message");

        // send text to all emergency contacts
        for (int i = 0; i < 5; i++) {
            if (!contacts.get(i).equals(null)) {
                smsManager.sendTextMessage(contacts.get(i).getContactNumber(), null, sos, null, null);
            }
        }

        Toast.makeText(this.getContext(), "SOS sent", Toast.LENGTH_LONG).show();
    }
}
