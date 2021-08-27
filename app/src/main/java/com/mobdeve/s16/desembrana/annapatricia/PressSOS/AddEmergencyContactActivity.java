package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEmergencyContactActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText et_name, et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts_add);

        this.btnsave = findViewById(R.id.addDmergencyContact_btnsave);
        this.btncancel = findViewById(R.id.addDmergencyContact_btncancel);
        this.et_name = findViewById(R.id.addEmergencyContacts_etname);
        this.et_num = findViewById(R.id.addEmergencyContacts_etname);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(AddEmergencyContactActivity.this, "Please type something", Toast.LENGTH_LONG).show();
                }
                else {
                    finish();
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

    }
}
