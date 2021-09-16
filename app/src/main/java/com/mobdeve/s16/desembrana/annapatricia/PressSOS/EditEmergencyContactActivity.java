package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditEmergencyContactActivity extends AppCompatActivity {

    private String existingName, existingNum;

    private static final String TAG = "EditEmergencyContact1";

    private DbHelper helper;
    private ArrayList<Contact> contacts;
    private EmergencyContactsFragment viewHolder;

    public static final String
        CURRENT_NAME = "CURRENT_NAME",
        CURRENT_NUMBER = "CURRENT_NUMBER",
        CURRENT_ID = "CURRENT_ID";

    private Button btnsave, btndelete, btncancel;
    private EditText et_name, et_num;



    private ActivityResultLauncher<Intent> myActivityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent res = result.getData();
                        Boolean bPin = res.getBooleanExtra("pin_result", false);

                        if (res != null) {
                            if (bPin) {

                                Contact oldC = new Contact(existingName, existingNum);
                                Contact newC = new Contact(et_name.getText().toString(), et_num.getText().toString());

                                helper.updateContact(oldC, newC);

                                finish();
                            }
                        }
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.d(TAG, "Cancelled");
                    }
                }
            });

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts_edit);

        helper = new DbHelper(this);
        contacts = helper.getAllContactsDefault();
        viewHolder = new EmergencyContactsFragment();

        this.btnsave = findViewById(R.id.editEmergencyContacts_btnSave);
        this.btndelete = findViewById(R.id.editEmergencyContacts_btnDelete);
        this.btncancel = findViewById(R.id.editEmergencyContacts_btnCancel);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.editEmergencyContacts_etnum);

        Intent intent = getIntent();
        this.existingName = intent.getStringExtra(CURRENT_NAME);
        this.existingNum = intent.getStringExtra(CURRENT_NUMBER);
        int id = intent.getIntExtra(CURRENT_ID, 0);
        //Log.d("checker", String.valueOf(id));

        this.et_name.setText(existingName);
        this.et_num.setText(existingNum);

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
                        Log.d(TAG, "Saved");

                        // update contact in db

                        Intent i = new Intent(EditEmergencyContactActivity.this, EnterPinActivity.class);

                        myActivityResultLauncher.launch(i);
                    }
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(EditEmergencyContactActivity.this, EnterPinActivity.class);

                helper.deleteContact(getApplicationContext(), id);

                //startActivity(i);
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
