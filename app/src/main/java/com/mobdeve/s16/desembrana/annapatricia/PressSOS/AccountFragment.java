package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AccountFragment extends Fragment {

    private Button btneditname, btneditnum, btneditsos, btnclear;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}