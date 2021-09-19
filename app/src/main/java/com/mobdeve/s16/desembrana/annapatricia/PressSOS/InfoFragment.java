package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * This class displays the instruction fragment on how to use the app.
 */

public class InfoFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_one, container, false);

        Button next1 = (Button) view.findViewById(R.id.howto_next2);
        next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Info02Activity.class);
                InfoFragment.this.startActivity(intent);
            }
        });
        return view;
    }
}
