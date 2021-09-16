package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEmergencyContactActivity extends AppCompatActivity {

    private DbHelper helper;

    private Button btnsave, btncancel;
    private EditText et_name, et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts_add);

        helper = new DbHelper(this);

        this.btnsave = findViewById(R.id.addDmergencyContact_btnsave);
        this.btncancel = findViewById(R.id.addDmergencyContact_btncancel);
        this.et_name = findViewById(R.id.addEmergencyContacts_etname);
        this.et_num = findViewById(R.id.addEmergencyContacts_etnum);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_name.getText().toString().isEmpty() || et_num.getText().toString().isEmpty()){
                    Toast.makeText(AddEmergencyContactActivity.this, "Please fill up all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    if(et_num.length() < 11){
                        Toast.makeText(AddEmergencyContactActivity.this, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent i = new Intent(AddEmergencyContactActivity.this, EnterPinActivity.class);

                        String name = et_name.getText().toString().trim();
                        String num = et_num.getText().toString().trim();

                        boolean result = helper.insertContact(new Contact(name, num));

                        finishAddContact(result);

                        startActivity(i);
                        finish();
                    }
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

    private void finishAddContact(boolean result) {
        if(result)
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Failed to Add Contact", Toast.LENGTH_SHORT).show();
    }
}
