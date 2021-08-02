package com.example.checkpoint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.Toast;

public class AddPooja extends AppCompatActivity
{

    EditText pj_name,pj_price,pj_date,pj_desc;
    Button add_pj;
    DatabaseReference pl_ref;
    PoojaList pl;
    Button button2;

    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pooja);

        pj_name= (EditText)findViewById(R.id.p_name);
        pj_price= (EditText)findViewById(R.id.p_price);
        pj_date= (EditText)findViewById(R.id.p_date);
        pj_desc= (EditText)findViewById(R.id.p_desc);
        add_pj= (Button)findViewById(R.id.btn_addp);
        button2 = (Button)findViewById(R.id.button2);

        pl_ref= FirebaseDatabase.getInstance().getReference().child("PoojaList");

        add_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name= pj_name.getText().toString().trim();
                String price= pj_price.getText().toString().trim();
                String date= pj_date.getText().toString().trim();
                String desc= pj_desc.getText().toString().trim();
                pl= new PoojaList(name, price, date, desc);
                pl_ref.push().setValue(pl);
                Toast.makeText(AddPooja.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}