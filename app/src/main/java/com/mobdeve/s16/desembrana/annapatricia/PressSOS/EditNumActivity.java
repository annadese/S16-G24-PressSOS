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

public class EditNumActivity extends AppCompatActivity {

    private static final String TAG = "EditNumActivity1";

    private Button btnsave, btncancel;
    private EditText et_num;
    private String num;

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
                                editor.putString(Keys.NUMBER_KEY.name(), et_num.getText().toString());
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
        setContentView(R.layout.activity_main_settings_edit_number);

        this.btnsave = findViewById(R.id.editnum_btnsave);
        this.btncancel = findViewById(R.id.editnum_btncancel);
        this.et_num = findViewById(R.id.editnum_ptname);

        SharedPreferences sp = getSharedPreferences(AppPreferences.SP_FILE_NAME, Context.MODE_PRIVATE);

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
                        Log.d(TAG, "Saved");

                        Intent i = new Intent(EditNumActivity.this, EnterPinActivity.class);
                        myActivityResultLauncher.launch(i);
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
