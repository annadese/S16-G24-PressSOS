package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterPinActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText pin;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        this.pin = findViewById(R.id.pin_etpin);
        this.btnsave = findViewById(R.id.pin_btnsave);
        this.btncancel = findViewById(R.id.pin_btncancel);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(pin.getText().toString().isEmpty()){
                    Toast.makeText(EnterPinActivity.this, "Please type something", Toast.LENGTH_LONG).show();
                }
                else {
                    if(pin.length() < 6){
                        Toast.makeText(EnterPinActivity.this, "PIN must be 6 characters", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if (!checkPin(pin.getText().toString())) {
                            Toast.makeText(EnterPinActivity.this, "Incorrect PIN", Toast.LENGTH_LONG).show();
                        } else {
                            Intent i = new Intent();
                            i.putExtra("pin_result", true);
                            setResult(Activity.RESULT_OK, i);
                            finish();
                        }
                    }
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent();
                i.putExtra("pin_result", false);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }

    /**
     * Checks if PIN is the same as the saved PIN in the sharedPreferences
     * @param pin - the pin to be checked
     * @return - true if PIN is the same; false if PIN is not the same
     */
    private Boolean checkPin (String pin) {
        Boolean bool = false;

        SharedPreferences sp = getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        String sp_pin = sp.getString(Keys.PIN_KEY.name(), "PIN");

        if (pin.equals(sp_pin)) {
            bool = true;
        }

        return bool;
    }
}
