package com.example.EventRegistrationApp.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.EventRegistrationApp.AdapterClasses.MyAdapter;
import com.example.EventRegistrationApp.ModelClasses.Model;
import com.example.EventRegistrationApp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference pl_ref;
    private MyAdapter adapter;
    private ArrayList<Model> list;
    TextView txtview;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtview = findViewById(R.id.textView8);
        home = findViewById(R.id.btn_home);

        pl_ref= FirebaseDatabase.getInstance().getReference().child("PoojaList");
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);

        recyclerView.setAdapter(adapter);
        pl_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowActivity.this, Admin.class));
            }
        });
    }
}