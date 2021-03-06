package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class bni extends AppCompatActivity {
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bni);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user_id = 1;
            } else {
                user_id= extras.getInt("user_id");
            }
        } else {
            user_id= savedInstanceState.getInt("item_id");
        }

        Button home = (Button) findViewById(R.id.bni_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    void goHome() {
        Intent intent= new Intent(bni.this, Main_Menu.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}