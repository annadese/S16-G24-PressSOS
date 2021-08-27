package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterLocation extends RecyclerView.Adapter<ViewHolderLocation> {

    private ArrayList<Location> locations;

    public AdapterLocation(ArrayList<Location> locations){
        this.locations = locations;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderLocation onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_location, parent, false);

        ViewHolderLocation viewHolderLocation = new ViewHolderLocation(itemView);

        return viewHolderLocation;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderLocation holder, int position) {
        holder.setTvLocation(locations.get(position).getLocationName());
        holder.setTvTime(locations.get(position).getTime().toString());
    }

    @Override
    public int getItemCount() {
        return this.locations.size();
    }
}
