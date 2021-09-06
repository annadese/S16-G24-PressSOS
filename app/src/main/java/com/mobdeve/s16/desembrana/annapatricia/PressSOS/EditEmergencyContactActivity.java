package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditEmergencyContactActivity extends AppCompatActivity {

    private Button btnsave, btndelete, btncancel;
    private EditText et_name, et_num;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts_edit);

        this.btnsave = findViewById(R.id.editEmergencyContacts_btnSave);
        this.btndelete = findViewById(R.id.editEmergencyContacts_btnDelete);
        this.btncancel = findViewById(R.id.editEmergencyContacts_btnCancel);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.editEmergencyContacts_etnum);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_name.getText().toString().isEmpty() || et_num.getText().toString().isEmpty()){
                    Toast.makeText(EditEmergencyContactActivity.this, "Please fill up all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    if(et_num.length() < 11){
                        Toast.makeText(EditEmergencyContactActivity.this, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent i = new Intent(EditEmergencyContactActivity.this, EnterPinActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(EditEmergencyContactActivity.this, EnterPinActivity.class);
                startActivity(i);
                finish();
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
