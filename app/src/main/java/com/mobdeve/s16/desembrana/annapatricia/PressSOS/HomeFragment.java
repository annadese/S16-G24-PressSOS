package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class HomeFragment extends Fragment{
    private Button btnSOS;
    private Switch btnAlarm;
    private MediaPlayer alarmSound;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.alarmSound = MediaPlayer.create(getActivity(), R.raw.alarm);
        this.btnSOS = (Button)view.findViewById(R.id.main_btnsos);
        this.btnAlarm = (Switch)view.findViewById(R.id.main_btnalarm);

        this.btnSOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alarm(); // alarm sound is activated
                sendTextMessage();
            }
        });
        return view;
    }


    // function for the alarm sound
    public void alarm(){
        if (alarmSound.isPlaying()) { // alarm sound stops if the button is clicked while the alarm is playing
            alarmSound.pause();
            alarmSound.seekTo(0);
        } else if(btnAlarm.isChecked()){ // alarm sound starts if the button is clicked while the alarm is off.
            alarmSound.setLooping(true);
            alarmSound.start();
        }
    }

    protected void sendTextMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage();
        Toast.makeText(this.getContext(), "SOS sent", Toast.LENGTH_LONG).show();
    }
}
