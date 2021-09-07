package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

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
                        Toast.makeText(EnterPinActivity.this, "Pin must be 6 characters", Toast.LENGTH_LONG).show();
                    }
                    else{
                        finish(); // to be used shared preferences
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
