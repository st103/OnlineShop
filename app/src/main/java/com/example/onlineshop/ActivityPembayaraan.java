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

}//class

