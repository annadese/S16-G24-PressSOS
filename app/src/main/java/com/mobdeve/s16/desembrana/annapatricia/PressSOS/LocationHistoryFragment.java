package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LocationHistoryFragment extends Fragment {

    private DbHelper helper;
    private ArrayList<Location> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterLocation lAdapter;
    //private DataHelperLocations dhl = new DataHelperLocations();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_history, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.location_recyclerView);

        helper = new DbHelper(getContext());

        this.lAdapter = new AdapterLocation(helper.getAllLocationsDefault());
        this.recyclerView.setAdapter(this.lAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}