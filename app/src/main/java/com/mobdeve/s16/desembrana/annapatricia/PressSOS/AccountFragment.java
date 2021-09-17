package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment {

    private DbHelper helper;

    private Button btneditname, btneditnum, btneditsos, btnclear;
    private TextView tvname, tvnum, tvsos;
    private String name, contactnum;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        helper = new DbHelper(getContext());

        this.btneditname = (Button) view.findViewById(R.id.mainset_btnname);
        this.btneditnum = (Button) view.findViewById(R.id.mainset_btnnum);
        this.btneditsos = (Button) view.findViewById(R.id.mainset_btnsos);
        this.btnclear = (Button) view.findViewById(R.id.mainset_btnclear);

        this.tvname = (TextView) view.findViewById(R.id.mainset_tvname);
        this.tvnum = (TextView) view.findViewById(R.id.mainset_tvnum);

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
                helper.deleteAllLocations();
            }
        });

        SharedPreferences sp = this.getActivity().getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        name = sp.getString(Keys.NAME_KEY.name(), "Name");
        contactnum = sp.getString(Keys.NUMBER_KEY.name(), "Contact Number");

        this.tvname.setText(name);
        this.tvnum.setText(contactnum);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = this.getActivity().getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        Log.d("AccountFragment1: ", sp.getString(Keys.NAME_KEY.name(), name));
        this.tvname.setText(sp.getString(Keys.NAME_KEY.name(), name));
        this.tvnum.setText(sp.getString(Keys.NUMBER_KEY.name(), contactnum));
    }
}