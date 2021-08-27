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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmergencyContactsFragment extends Fragment {

    private ArrayList<Contact> data = new ArrayList<>();
    private FloatingActionButton btnaddcontact;
    private RecyclerView recyclerView;
    private AdapterContacts cAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency_contacts_main, container, false);

        this.data = DataHelper.loadEmergencyContactData();
        this.btnaddcontact = (FloatingActionButton) view.findViewById(R.id.addContactFab);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);

        btnaddcontact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddEmergencyContactActivity.class);
                EmergencyContactsFragment.this.startActivity(intent);
            }
        });

        this.cAdapter = new AdapterContacts(data);
        this.recyclerView.setAdapter(this.cAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
