package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNumActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText et_num;
    private String num;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings_edit_number);

        this.btnsave = findViewById(R.id.editnum_btnsave);
        this.btncancel = findViewById(R.id.editnum_btncancel);
        this.et_num = findViewById(R.id.editnum_ptname);

        SharedPreferences sp = getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        num = sp.getString(Keys.NUMBER_KEY.name(), "Name");
        this.et_num.setText(num);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_num.getText().toString().isEmpty()){
                    Toast.makeText(EditNumActivity.this, "Please type something", Toast.LENGTH_LONG).show();
                }
                else {
                    if(et_num.length() < 11){
                        Toast.makeText(EditNumActivity.this, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent i = new Intent(EditNumActivity.this, EnterPinActivity.class);
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
}
