package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditEmergencyContactActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText et_name, et_num;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts_edit);

        this.btnsave = findViewById(R.id.editContact_btnSave);
        this.btncancel = findViewById(R.id.editContact_btnCancel);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.editEmergencyContacts_etnum);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(EditEmergencyContactActivity.this, "Please type something", Toast.LENGTH_LONG).show();
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
