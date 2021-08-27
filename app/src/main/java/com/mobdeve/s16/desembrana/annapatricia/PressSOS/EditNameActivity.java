package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNameActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText et_name;
    private Account account;
    private String name;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings_edit_name);

        this.btnsave = findViewById(R.id.editname_btnsave);
        this.btncancel = findViewById(R.id.editname_btncancel);
        this.et_name = findViewById(R.id.editname_ptname);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(EditNameActivity.this, "Please type something", Toast.LENGTH_LONG).show();
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
