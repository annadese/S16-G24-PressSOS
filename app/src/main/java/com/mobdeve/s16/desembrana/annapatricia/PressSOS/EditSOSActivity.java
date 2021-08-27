package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditSOSActivity extends AppCompatActivity {

    private Button btnsave, btncancel;
    private EditText et_sos;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings_sos);

        this.btnsave = findViewById(R.id.editsos_btnsave);
        this.btncancel = findViewById(R.id.editsos_btncancel);
        this.et_sos = findViewById(R.id.editsos_ptsos);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et_sos.getText().toString().isEmpty()){
                    Toast.makeText(EditSOSActivity.this, "Please type something", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent(EditSOSActivity.this, EnterPinActivity.class);
                    startActivity(i);
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
