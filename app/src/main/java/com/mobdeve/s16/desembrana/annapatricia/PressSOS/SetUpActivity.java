package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetUpActivity extends AppCompatActivity {

    private DbHelper helper;
    private static final String TAG = "SetUpActivity";
    public static final String SP_FILE_NAME = "sp";

    private Button btnsave;
    private EditText et_name, et_num, et_pin1, et_pin2, et_ename, et_enum;
    private String name, num;
    private String pin1, pin2;
    private Account account;
    private Boolean hasAccount;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_account);

        helper = new DbHelper(this);

        this.btnsave = findViewById(R.id.setup_btn);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.setup_ptcontact);
        this.et_pin1 = findViewById(R.id.setup_pwpin1);
        this.et_pin2 = findViewById(R.id.setup_pwpin2);
        this.et_ename = findViewById(R.id.setup_ptemergencyname);
        this.et_enum = findViewById(R.id.setup_ptemergencynum);
        this.hasAccount = false;

        SharedPreferences sp = getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        hasAccount = sp.getBoolean(Keys.ACCOUNT_KEY.name(), this.hasAccount);
        Log.d("SetUpActivity1: ", hasAccount.toString());

        // if an account already exists, skip Setup Activity
        if (hasAccount) {
            Intent i = new Intent(SetUpActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // validation for Set Up
                if(et_name.getText().toString().isEmpty() || et_num.getText().toString().isEmpty() || et_pin1.getText().toString().isEmpty() || et_pin2.getText().toString().isEmpty() || et_ename.getText().toString().isEmpty() || et_enum.getText().toString().isEmpty()) {
                    Toast.makeText(SetUpActivity.this, "Please fill up all fields", Toast.LENGTH_LONG).show();
                }
                else{
                    if(et_num.length() < 11 || et_enum.length() < 11){
                        Toast.makeText(SetUpActivity.this, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    } 
                    else{
                        if(et_pin1.length() != 6 || et_pin2.length() != 6){
                            Toast.makeText(SetUpActivity.this, "PIN must be 6 digits", Toast.LENGTH_LONG).show();
                        }
                        else{
                            pin1 = et_pin1.getText().toString();
                            pin2 = et_pin2.getText().toString();

                            if(!pin1.equals(pin2)){
                                Toast.makeText(SetUpActivity.this, "PIN does not match", Toast.LENGTH_LONG).show();
                            }
                            else{
                                name = et_name.getText().toString().trim();
                                num = et_num.getText().toString().trim();
                                pin1 = et_pin1.getText().toString();

                                account = new Account(name, num, pin1);

                                SharedPreferences sp = getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();

                                editor.putBoolean(Keys.ACCOUNT_KEY.name(), true);
                                editor.putString(Keys.NAME_KEY.name(), account.getName());
                                editor.putString(Keys.NUMBER_KEY.name(), account.getNum());
                                editor.putString(Keys.SOS_MESSAGE_KEY.name(), account.getSosText());
                                editor.putString(Keys.PIN_KEY.name(), account.getPin());

                                editor.apply();

                                // Add

                                String name = et_ename.getText().toString().trim();
                                String num = et_enum.getText().toString().trim();

                                boolean result = helper.insertContact(new Contact(name, num));

                                finishAddContact(result);

                                Intent i = new Intent(SetUpActivity.this, InfoActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                }
                /*if(et_name.getText().toString().isEmpty() || et_num.getText().toString().isEmpty() || et_pin1.getText().toString().isEmpty() || et_pin2.getText().toString().isEmpty() || et_ename.getText().toString().isEmpty() || et_enum.getText().toString().isEmpty()){
                    Toast.makeText(SetUpActivity.this, "Please fill up all fields", Toast.LENGTH_LONG).show();
                }

                else{
                    name = et_name.getText().toString();
                    num = et_num.getText().toString();
                    pin1 = Integer.parseInt(et_pin1.getText().toString());
                    pin2 = Integer.parseInt(et_pin2.getText().toString());

                    if(pin1 == pin2){
                        account = new Account(name, num, pin1);
                        Intent i = new Intent(SetUpActivity.this, MainActivity.class);
                        startActivity(i);
                    }

                    else {
                        Toast.makeText(SetUpActivity.this, "PIN does not match", Toast.LENGTH_LONG).show();
                    }
                }*/
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
