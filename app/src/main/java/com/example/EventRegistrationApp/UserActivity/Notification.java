package com.example.EventRegistrationApp.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EventRegistrationApp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Notification extends AppCompatActivity {

    TextView name,date;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        name= (TextView) findViewById(R.id.name);
        date= (TextView)findViewById(R.id.date);

        btnAdd= findViewById(R.id.btn_add);

        Intent intent = getIntent();
//        String poojaname= intent.getStringExtra("nameINTENT");
//        String poojadate = intent.getStringExtra("dateINTENT");
        name.setText( getIntent().getStringExtra("nameINTENT"));
        date.setText( getIntent().getStringExtra("dateINTENT"));

        btnAdd.setOnClickListener(new View.OnClickListener() {


            long startTime = 0, endTime=0;

            @Override
            public void onClick(View v) {
                if(!name.getText().toString().isEmpty() && !date.getText().toString().isEmpty())
                {

                    String startDate = date.getText().toString();
                    try {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
                        startTime=date.getTime();
                    }
                    catch(Exception e){ }

                    Calendar cal = Calendar.getInstance();
                    Intent intent= new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, name.getText().toString());
                    intent.putExtra(CalendarContract.Events.TITLE, name.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra("beginTime",startTime);
                    if(intent.resolveActivity(getPackageManager())!=null)
                    {
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Notification.this, "No support for this action", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Notification.this, "Fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}