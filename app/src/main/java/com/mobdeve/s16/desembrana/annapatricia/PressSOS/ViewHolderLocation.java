package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderLocation extends RecyclerView.ViewHolder {

    private TextView tvLocation;
    private TextView tvTime;
    private Button btnView;

    public ViewHolderLocation(@NonNull View itemView) {
        super(itemView);

        this.tvLocation = itemView.findViewById(R.id.viewholder_tvLoc);
        this.tvTime = itemView.findViewById(R.id.viewholder_tvTime);
    }

    public void setTvLocation(Llocation location) {
        this.tvLocation.setText(location.getLatitude() + ", " + location.getLongitude());
    }

    public void setTvTime(String time) {
        this.tvTime.setText(time);
    }
}
