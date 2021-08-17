package com.example.EventRegistrationApp.AdminActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.EventRegistrationApp.ModelClasses.PoojaList;
import com.example.EventRegistrationApp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.Toast;

import java.util.Calendar;

public class AddPooja extends AppCompatActivity {

    EditText pj_name, pj_price, pj_date, pj_desc;
    Button add_pj;
    DatabaseReference pl_ref;
    PoojaList pl;
    Button button2;
    boolean valid = true;
    String name, price, date, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pooja);

        pj_name = (EditText) findViewById(R.id.p_name);
        pj_price = (EditText) findViewById(R.id.p_price);
        pj_date = (EditText) findViewById(R.id.p_date);
        pj_date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    pj_date.setText(current);
                    pj_date.setSelection(sel < current.length() ? sel : current.length());

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        pj_desc = (EditText) findViewById(R.id.p_desc);
        add_pj = (Button) findViewById(R.id.btn_addp);
        button2 = (Button) findViewById(R.id.button2);

        pl_ref = FirebaseDatabase.getInstance().getReference().child("PoojaList");

        add_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(pj_name);
                checkField(pj_date);
                checkField(pj_price);
                checkField(pj_desc);
                if (valid) {
                    name = pj_name.getText().toString().trim();
                    price = pj_price.getText().toString().trim();
                    date = pj_date.getText().toString().trim();
                    desc = pj_desc.getText().toString().trim();
                    pl = new PoojaList(name, price, date, desc);
                    //pl_ref.push().setValue(pl);
                    pl_ref.child(name).setValue(pl);
                    Toast.makeText(AddPooja.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ShowActivity.class));
                }
            }
        });
    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}