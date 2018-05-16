package com.example.dc.spinnerapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
Button buttonRegister;
Button buttonLogin;
EditText editTextUsername;
EditText editTextPassword;
    String username;
    String password;
    private static final String TAG = "LoginActivity";
    private usernamesDB v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        v1=new usernamesDB(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));


            }
        });

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if( validateUser(editTextUsername.getText().toString(), editTextPassword.getText().toString()) == true){
                       Intent i = new Intent(LoginActivity.this,Login_SuccessActivity.class);
                       i.putExtra("username",editTextUsername.getText().toString());
                        startActivity(i);

                    }
                    else
                        Toast.makeText(LoginActivity.this, "Wrong username or password, please try again", Toast.LENGTH_SHORT).show();
            }
        });
        username = "msjahun";
        password = "123";


    }

    Boolean validateUser(String tusername, String tpassword) {
        Log.d(TAG, "validateUser: username = "+ tusername + " password = "+tpassword+ "username = "+ username + " password = "+password);
    if( getUserDetails(tusername, tpassword)){

        return true;
    }else
    return false;
    }


    private Boolean getUserDetails(String username, String password) {

      if(validateInput(username,password)) {
          SQLiteDatabase db = v1.getReadableDatabase();
          Cursor c = db.rawQuery("SELECT * FROM usernames_table WHERE username='" + username + "'   and  password= '" + password + "'", null);
          if (c.moveToFirst()) {
             return true;
          } else {
             return false;

          }
      }

      return false;

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
