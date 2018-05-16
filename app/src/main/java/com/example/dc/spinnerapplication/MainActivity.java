package com.example.dc.spinnerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMain, spinnerCountryCode;
    ArrayAdapter adapter;
    String [] items = {"Female", "Male"};
    String [] countryCode = {"+90", "+011", "+234", "+55", "+447"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMain = (Spinner) findViewById(R.id.spinnerMain);
        spinnerCountryCode = (Spinner) findViewById(R.id.spinnerCountryCode);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryCode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountryCode.setAdapter(adapter);


    }
}
