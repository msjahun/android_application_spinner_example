package com.example.dc.spinnerapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login_SuccessActivity extends AppCompatActivity {
Button buttonLogout;
TextView textViewUsername;
TextView textViewGender;
String username;
    private usernamesDB v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__success);

        Bundle bundle = getIntent().getExtras();
        username = (String)bundle.getString("username");


        v1=new usernamesDB(this);
textViewGender = (TextView) findViewById(R.id.textViewGender);
textViewUsername = (TextView) findViewById( R.id.textViewUsername);

        dataview(username);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_SuccessActivity.this,LoginActivity.class));
            }
        });



    }

    private void dataview(String username) {


        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usernames_table WHERE username='" + username + "'", null);
        if (c.moveToFirst()) {
            textViewUsername.setText(c.getString(0));
            textViewGender.setText(c.getString(2));
        } else {
            Toast.makeText(getApplicationContext(), "Not a valid user account!!!", Toast.LENGTH_SHORT).show();

        }

    }
    }
