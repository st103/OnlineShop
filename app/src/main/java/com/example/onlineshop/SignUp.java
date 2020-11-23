package com.example.onlineshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    Database db;
    EditText new_user,new_pass,new_repass,new_phone;
    Button btncreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new Database(this);
        new_user=(EditText)findViewById(R.id.username);
        new_pass=(EditText)findViewById(R.id.password);
        new_repass=(EditText)findViewById(R.id.repassword);
        new_phone=(EditText)findViewById(R.id.phone);
        btncreate=(Button)findViewById(R.id.create);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = new_user.getText().toString();
                String s2 = new_pass.getText().toString();
                String s3 = new_repass.getText().toString();
                String s4 = new_phone.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty. Please fill it.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s2.equals(s3)){
                        Boolean chkusername = db.checkusername(s1);
                        if (chkusername==true){
                            Boolean insert = db.insert(s1,s2,s4);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Your account are registered.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Username are already exists.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password do not match. Please re-type again.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

