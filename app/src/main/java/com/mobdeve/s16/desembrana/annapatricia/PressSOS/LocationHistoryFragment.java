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

import java.util.ArrayList;

public class LocationHistoryFragment extends Fragment {

    private ArrayList<Contact> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterContacts lAdapter;
    private Button btnviewlocation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_history, container, false);

        this.btnviewlocation = view.findViewById(R.id.viewholder_btnView);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);
        btnviewlocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddEmergencyContactActivity.class);
                LocationHistoryFragment.this.startActivity(intent);
            }
        });

        this.lAdapter = new AdapterContacts(data);
        this.recyclerView.setAdapter(this.lAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}