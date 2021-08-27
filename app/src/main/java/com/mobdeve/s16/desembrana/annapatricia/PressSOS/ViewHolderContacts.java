package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ViewHolderContacts extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvContactNumber;
    private Button btnEdit;

    public ViewHolderContacts(@NonNull @NotNull View itemView) {
        super(itemView);

        this.tvName = itemView.findViewById(R.id.vhcontacts_tvname);
        this.tvContactNumber = itemView.findViewById(R.id.vhcontacts_number);
        this.btnEdit = itemView.findViewById(R.id.vhcontacts_btnedit);
    }

    public void setTvName(String name) {
        this.tvName.setText(name);
    }

    public void setTvContactNumber(String contactNumber) {
        this.tvContactNumber.setText(contactNumber);
    }

}
