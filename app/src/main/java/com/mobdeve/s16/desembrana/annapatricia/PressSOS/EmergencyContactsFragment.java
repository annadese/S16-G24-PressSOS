package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmergencyContactsFragment extends Fragment {

    private DbHelper helper;
    private ArrayList<Contact> data = new ArrayList<>();
    private FloatingActionButton btnaddcontact;
    private RecyclerView recyclerView;
    private AdapterContacts cAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency_contacts_main, container, false);

        helper = new DbHelper(getContext());

        this.btnaddcontact = (FloatingActionButton) view.findViewById(R.id.addContactFab);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);

        btnaddcontact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                data = helper.getAllContactsDefault();

                if (data.size() >= 5) {
                    Toast.makeText(getActivity(), "Can only add up to 5 contacts", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), AddEmergencyContactActivity.class);
                    EmergencyContactsFragment.this.startActivity(intent);
                }
            }
        });

        this.cAdapter = new AdapterContacts(helper.getAllContactsDefault());
        this.recyclerView.setAdapter(this.cAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.cAdapter = new AdapterContacts(helper.getAllContactsDefault());
        this.recyclerView.setAdapter(this.cAdapter);
    }
}