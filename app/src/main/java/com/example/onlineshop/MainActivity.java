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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //EditText Username, Password;
    //Button Login, Signup;
    //Database db;
    DatabaseManager db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseManager(this);
        /*Username = (EditText) findViewById(R.id.txt_login_username);
        Password = (EditText) findViewById(R.id.txt_login_password);
        Login = (Button) findViewById(R.id.btn_login);
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
        });*/

        db = new DatabaseManager(this);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        Button btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(view -> {
            TextView username = (TextView) findViewById(R.id.txt_login_username);
            TextView password = (TextView) findViewById(R.id.txt_login_password);
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //      .setAction("Action", null).show();
                if(db.combinationCorrect(username.getText().toString(), password.getText().toString())) {
                openDatabaseManager();
                } else {
                    Toast.makeText(getBaseContext(),"Username atau Password Salah", Toast.LENGTH_LONG).show();
                }

        });
    }

    protected void openDatabaseManager() {
        Intent intent = new Intent(this, ManageDatabase.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}