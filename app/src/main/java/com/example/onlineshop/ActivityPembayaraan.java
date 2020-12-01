package com.example.onlineshop;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPembayaraan extends AppCompatActivity {
    int user_id;

    Button btnPay;

    RadioGroup radPayment;
    RadioButton radBni;
    RadioButton radBri;
    RadioButton radAlfamart;
    RadioButton radIndomaret;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

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

//binding XMl controls with Java code
        btnPay = (Button) findViewById(R.id.btnPay);
        radPayment = (RadioGroup) findViewById(R.id.radioGroupPayment);
        radBni = (RadioButton) findViewById(R.id.radBni);
        radBri = (RadioButton) findViewById(R.id.radBri);
        radAlfamart = (RadioButton) findViewById(R.id.radAlfamart);
        radIndomaret = (RadioButton) findViewById(R.id.radIndomaret);

        //LISTENER: wiring button-events-&-code
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Payment Via ";
                int radioId = radPayment.getCheckedRadioButtonId();
// compare selected's Id with individual RadioButtons ID
                if (radBni.isChecked()) {
                    btnPay = (Button) findViewById(R.id.btnPay);
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), bni.class);
                            payItems();
                            startActivityForResult(myIntent, 1);

                        }
                    });
                    msg = msg + "BNI";
                }
// similarly you may use .isChecked() on each RadioButton
                else if (radBri.isChecked()) {
                    btnPay = (Button) findViewById(R.id.btnPay);
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), bri.class);
                            payItems();
                            startActivityForResult(myIntent, 2);
                        }
                    });
                    msg = msg + "BRI";
                }
// similarly you may use .isChecked() on each RadioButton
                else if (radAlfamart.isChecked()) {
                    btnPay = (Button) findViewById(R.id.btnPay);
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), alfamart.class);
                            payItems();
                            startActivityForResult(myIntent, 3);
                        }
                    });
                    msg = msg + "ALFAMART";
                }
                // similarly you may use .isChecked() on each RadioButton
                else if (radIndomaret.isChecked()) {
                    btnPay = (Button) findViewById(R.id.btnPay);
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), indomaret.class);
                            payItems();
                            startActivityForResult(myIntent, 4);
                        }
                    });
                    msg = msg + "INDOMARET";
                }

                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT).show();
//go now and compute cost...
            }//onClick

        });
    }//onCreate

    void payItems() {
        Database db = new Database(this);
        db.buyItem(user_id);
    }

}//class

