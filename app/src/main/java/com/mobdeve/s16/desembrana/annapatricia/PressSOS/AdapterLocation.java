package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLocation extends RecyclerView.Adapter<ViewHolderLocation> {

    private ArrayList<Llocation> locations;

    public AdapterLocation(ArrayList<Llocation> locations){
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

                String link = "https://www.google.com/maps/dir/?api=1&destination=" +
                        locations.get(viewHolderLocation.getAdapterPosition()).getLatitude() + "," +
                        locations.get(viewHolderLocation.getAdapterPosition()).getLongitude();

                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(link));

                parent.getContext().startActivity(viewIntent);
            }
        });

        return viewHolderLocation;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLocation holder, int position) {
        holder.setTvLocation(locations.get(position));
        holder.setTvTime(locations.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return this.locations.size();
    }
}
