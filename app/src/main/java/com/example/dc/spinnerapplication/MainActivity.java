package com.example.dc.spinnerapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMain, spinnerCountryCode;
    ArrayAdapter adapter;
    String [] items = {"Female", "Male"};
    String [] countryCode = {"+90", "+011", "+234", "+55", "+447"};
    Button buttonLogin;
    Button buttonRegister;
    EditText editTextusername;
    EditText editTextPassword;

    CheckBox checkBoxTermandConditions;
    private static final String TAG = "MainActivity";
    private usernamesDB v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=new usernamesDB(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextusername = (EditText) findViewById(R.id.editTextUsername);
        checkBoxTermandConditions = (CheckBox) findViewById(R.id.checkBoxTermsAndConditions);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxTermandConditions.isChecked()){


                    addUser(editTextusername.getText().toString(), editTextPassword.getText().toString(),spinnerMain.getSelectedItem().toString());
                    Toast.makeText(MainActivity.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Login_SuccessActivity.class);
                    i.putExtra("username",editTextusername.getText().toString());
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Please accept the terms and conditions before registering", Toast.LENGTH_SHORT).show();
                }

            }
        });
        spinnerMain = (Spinner) findViewById(R.id.spinnerMain);
        spinnerCountryCode = (Spinner) findViewById(R.id.spinnerCountryCode);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryCode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountryCode.setAdapter(adapter);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });


    }

    private void addUser(String username, String password, String gender) {

       if(validateInput(username,"Username") && validateInput(password, "Password")  && validateInput(gender, "Gender")){
           SQLiteDatabase db =v1.getWritableDatabase();
           db.execSQL("INSERT INTO usernames_table VALUES('"+username+"','"+password+"','"+gender+"');");
       }



    }




    private boolean validateInput(String s, String controlName){
        Log.d(TAG, "validateInput: is called");
        if (s.trim().length()==0){
            Toast.makeText(this,"Please enter Valid "+controlName+"!", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }






}
