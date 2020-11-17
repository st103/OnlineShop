package com.example.onlineshop;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.onlineshop.bni;
import com.example.onlineshop.bri;
import com.example.onlineshop.alfamart;
import com.example.onlineshop.indomaret;
import com.example.onlineshop.R;

public class MainActivity extends AppCompatActivity {
    Button btnbni,btnbri,btnalfamart,btnindomaret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnbni  = (Button) findViewById(R.id.btnbni);
        btnbni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),bni.class);
                startActivityForResult(myIntent,0);
            }
        });

        btnbri  = (Button) findViewById(R.id.btnbri);
        btnbri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),bri.class);
                startActivityForResult(myIntent,0);
            }
        });

        btnalfamart  = (Button) findViewById(R.id.btnalfamart);
        btnalfamart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),alfamart.class);
                startActivityForResult(myIntent,0);
            }
        });

        btnindomaret  = (Button) findViewById(R.id.btnindomaret);
        btnindomaret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),indomaret.class);
                startActivityForResult(myIntent,0);
            }
        });

    }}
