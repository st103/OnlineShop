package com.example.onlineshop;

import android.os.Bundle;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ManageDatabase extends Activity {

    DatabaseManager dm;
    EditText nama, hobi, GetId, updateNama, updateHobi, idDel;
    Button addBtn, getIdBtn, updateBtn, delBtn;
    TableLayout tabel4data;// tabel for data20:


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_database);
        dm = new DatabaseManager(this);
        setupView();
        fungsiBtn();
        updateTable();
    }

    public void setupView() {
        tabel4data = (TableLayout) findViewById(R.id.tabel_data);
        nama = (EditText) findViewById(R.id.inNama);
        hobi = (EditText) findViewById(R.id.inHobi);updateNama= (EditText) findViewById(R.id.inUpdateNama);
        updateHobi= (EditText) findViewById(R.id.inUpdateHobi);
        GetId= (EditText) findViewById(R.id.inGetId);
        idDel=(EditText)findViewById(R.id.idDelete);


        addBtn = (Button) findViewById(R.id.btnAdd);
        getIdBtn= (Button) findViewById(R.id.btnGetId);
        updateBtn= (Button) findViewById(R.id.btnUpdate);
        delBtn= (Button) findViewById(R.id.btnDel);
    }

    public void fungsiBtn() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpKamuta();
            }

        });

    }

    protected void printDatabaseRowCount() {
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        TextView debug_txt =  (TextView) findViewById(R.id.debug_txt);
        if(data.isEmpty()) {
            debug_txt.setText(DatabaseManager.CREATE_TABLE);
        }
        else {
            debug_txt.setText(DatabaseManager.CREATE_TABLE);
        }
        debug_txt.setText(DatabaseManager.CREATE_TABLE);
    }

    protected void simpKamuta() {
        try {
            dm.addRow(nama.getText().toString(),hobi.getText().toString());
            Toast.makeText(getBaseContext(), nama.getText().toString() + ", berhasil disimpan", Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal simpan, " +e.toString(),Toast.LENGTH_LONG).show();
        }
        printDatabaseRowCount();
    }

    protected void kosongkanField(){
        nama.setText("");
        hobi.setText("");
    }

    protected void updateTable() {
        // TODO Auto-generated method stub
        while (tabel4data.getChildCount() > 1) {
            tabel4data.removeViewAt(1);
        }
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            TableRow tabelBaris = new TableRow(this);
            ArrayList<Object> baris = data.get(posisi);
            TextView idTxt = new TextView(this);
            idTxt.setText(baris.get(0).toString());
            tabelBaris.addView(idTxt);
            TextView namaTxt = new TextView(this);
            namaTxt.setText(baris.get(1).toString());
            tabelBaris.addView(namaTxt);
            TextView hobiTxt = new TextView(this);
            hobiTxt.setText(baris.get(2).toString());
            tabelBaris.addView(hobiTxt);
            tabel4data.addView(tabelBaris);
        }
    }

}