package com.example.EventRegistrationApp.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.EventRegistrationApp.LoginCreateAccount.MainActivity;
import com.example.EventRegistrationApp.R;
import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button btn_viewreg = findViewById(R.id.btn_viewreg);
        Button button3 = findViewById(R.id.button3);
        Button logout = findViewById(R.id.logoutBtn);
        TextView username = findViewById(R.id.textView3);
        Intent in = getIntent();
        username.setText("Welcome " + getIntent().getStringExtra("usernameINTENT") + "!");


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User.this, ShowActivityUser.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        btn_viewreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), DisplayRegistrations.class);
                startActivity(in);
            }
        });
    }
}

