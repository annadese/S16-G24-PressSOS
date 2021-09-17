package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterContacts extends RecyclerView.Adapter<ViewHolderContacts> {

    private ArrayList<Contact> contacts;

    public AdapterContacts(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolderContacts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_contacts, parent, false);

        ViewHolderContacts viewHolderContacts = new ViewHolderContacts(itemView);
        viewHolderContacts.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(parent.getContext(), EditEmergencyContactActivity.class);

                intent.putExtra("CURRENT_NAME", contacts.get(viewHolderContacts.getAdapterPosition()).getName());
                intent.putExtra("CURRENT_NUMBER", contacts.get(viewHolderContacts.getAdapterPosition()).getContactNumber());
                intent.putExtra("CURRENT_ID", contacts.get(viewHolderContacts.getAdapterPosition()).getId());

                parent.getContext().startActivity(intent);
            }
        });

        return viewHolderContacts;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContacts holder, int position) {
        holder.setTvName(contacts.get(position).getName());
        holder.setTvContactNumber(contacts.get(position).getContactNumber());
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }
}
