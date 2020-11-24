package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshop.R;

public class MainActivity extends AppCompatActivity {
    EditText Username, Password;
    Button Login, Signup;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.login);
        Signup = (Button) findViewById(R.id.signup);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                Boolean Chkuserpass = db.usernamepassword(username, password);
                if(Chkuserpass==true){
                    Intent intent= new Intent(MainActivity.this, Item_Detail.class);
                    startActivity(intent);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Login Successfully!");
                    builder.setMessage("Welcome, " + username + " to Chelsea Shop!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalide Username and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
