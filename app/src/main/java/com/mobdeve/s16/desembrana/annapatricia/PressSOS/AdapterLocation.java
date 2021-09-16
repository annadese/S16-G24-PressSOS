package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLocation extends RecyclerView.Adapter<ViewHolderLocation> {

    private ArrayList<Location> locations;

    public AdapterLocation(ArrayList<Location> locations){
        this.locations = locations;
    }

    @NonNull
    @Override
    public ViewHolderLocation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_location, parent, false);

        ViewHolderLocation viewHolderLocation = new ViewHolderLocation(itemView);
        viewHolderLocation.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), LocationActivity.class);
                parent.getContext().startActivity(intent);
            }
        });

        return viewHolderLocation;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLocation holder, int position) {
        holder.setTvLocation(locations.get(position).getLatitude());
        holder.setTvTime(locations.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return this.locations.size();
    }
}
