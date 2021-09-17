package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.app.Activity;
import android.content.Intent;
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

public class AddEmergencyContactActivity extends AppCompatActivity {

    private static final String TAG = "AddEmergencyContact1";

    private DbHelper helper;

    private Button btnsave, btncancel;
    private EditText et_name, et_num;

    private ActivityResultLauncher<Intent> myActivityResultLauncherAdd = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent res = result.getData();
                        Boolean bPin = res.getBooleanExtra("pin_result", false);

                        if (res != null) {
                            if (bPin) {
                                Contact newC = new Contact(et_name.getText().toString(), et_num.getText().toString());

                                String name = et_name.getText().toString().trim();
                                String num = et_num.getText().toString().trim();

                                helper.insertContact(new Contact(name, num));

                                finish();
                            }
                        }
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.d(TAG, "Cancelled");
                    }
                }
            });

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

                        myActivityResultLauncherAdd.launch(i);
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
}
