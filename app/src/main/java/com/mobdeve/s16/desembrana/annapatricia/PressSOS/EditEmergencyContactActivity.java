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

public class EditEmergencyContactActivity extends AppCompatActivity {

    private static final String TAG = "EditEmergencyContact1";

    public static final String
        CURRENT_NAME = "CURRENT_NAME",
        CURRENT_NUMBER = "CURRENT_NUMBER";

    private Button btnsave, btndelete, btncancel;
    private EditText et_name, et_num;

    private ActivityResultLauncher<Intent> myActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    SharedPreferences sp = getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent res = result.getData();
                        Boolean bPin = res.getBooleanExtra("pin_result", false);

                        if (res != null) {
                            if (bPin) {
                                editor.putString(Keys.NAME_KEY.name(), et_name.getText().toString());
                                editor.putString(Keys.NAME_KEY.name(), et_num.getText().toString());
                                editor.apply();
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

        this.btnsave = findViewById(R.id.editEmergencyContacts_btnSave);
        this.btndelete = findViewById(R.id.editEmergencyContacts_btnDelete);
        this.btncancel = findViewById(R.id.editEmergencyContacts_btnCancel);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.editEmergencyContacts_etnum);

        Intent intent = getIntent();
        String name = intent.getStringExtra(CURRENT_NAME);
        String contactNumber = intent.getStringExtra(CURRENT_NUMBER);

        this.et_name.setText(name);
        this.et_num.setText(contactNumber);

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
