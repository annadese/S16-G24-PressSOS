package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

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
                if(btnAlarm.isChecked()) {
                    alarmSound.start();
                }
            }
        });
        return view;
    }
}
