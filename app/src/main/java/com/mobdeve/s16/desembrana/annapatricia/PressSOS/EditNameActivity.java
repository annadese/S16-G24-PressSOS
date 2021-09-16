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
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.security.Key;

public class EditNameActivity extends AppCompatActivity {

    private static final String TAG = "EditNameActivity1";

    private Button btnsave, btncancel;
    private EditText et_name;
    private Account account;
    private String name;

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
        setContentView(R.layout.activity_main_settings_edit_name);

        this.btnsave = findViewById(R.id.editname_btnsave);
        this.btncancel = findViewById(R.id.editname_btncancel);
        this.et_name = findViewById(R.id.editname_ptname);

        SharedPreferences sp = getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);

        name = sp.getString(Keys.NAME_KEY.name(), "Name");
        this.et_name.setText(name);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(EditNameActivity.this, "Please type something", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d(TAG, "Saved");

                    Intent i = new Intent(EditNameActivity.this, EnterPinActivity.class);
                    myActivityResultLauncher.launch(i);
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
