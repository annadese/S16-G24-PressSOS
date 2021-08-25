package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AccountFragment extends Fragment {

    private Button btneditname, btneditnum, btneditsos, btnclear;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        this.btneditname = (Button) view.findViewById(R.id.mainset_btnname);
        this.btneditnum = (Button) view.findViewById(R.id.mainset_btnnum);
        this.btneditsos = (Button) view.findViewById(R.id.mainset_btnsos);
        this.btnclear = (Button) view.findViewById(R.id.mainset_btnclear);

        btneditname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), EditNameActivity.class);
                AccountFragment.this.startActivity(intent);
            }
        });

        btneditnum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), EditNumActivity.class);
                AccountFragment.this.startActivity(intent);
            }
        });

        btneditsos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), EditSOSActivity.class);
                AccountFragment.this.startActivity(intent);
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "Cleared Location History", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}