package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetUpActivity extends AppCompatActivity {

    private Button btnsave;
    private EditText et_name, et_num, et_pin1, et_pin2, et_ename, et_enum;
    private String name, num, e_name;
    private int pin1, pin2, e_num;
    private Account account;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_account);

        this.btnsave = findViewById(R.id.setup_btn);
        this.et_name = findViewById(R.id.editEmergencyContacts_etname);
        this.et_num = findViewById(R.id.setup_ptcontact);
        this.et_pin1 = findViewById(R.id.setup_pwpin1);
        this.et_pin2 = findViewById(R.id.setup_pwpin2);
        this.et_ename = findViewById(R.id.setup_ptemergencyname);
        this.et_enum = findViewById(R.id.setup_ptemergencynum);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.getText().toString().isEmpty() || et_num.getText().toString().isEmpty() || et_pin1.getText().toString().isEmpty() || et_pin2.getText().toString().isEmpty() || et_ename.getText().toString().isEmpty() || et_enum.getText().toString().isEmpty()){
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
                }
            }
        });
    }
}
