package com.samkecy.nationalmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        String val = getIntent().getExtras().get("value").toString();

       Toast.makeText(getBaseContext(), "values click " + val,Toast.LENGTH_LONG).show();



    }
}
