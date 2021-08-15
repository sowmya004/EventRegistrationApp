package com.example.EventRegistrationApp.UserActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.EventRegistrationApp.AdapterClasses.MyAdapterUser;
import com.example.EventRegistrationApp.ModelClasses.UserView;
import com.example.EventRegistrationApp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import java.util.ArrayList;

public class ShowActivityUser extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference p_ref;
    MyAdapterUser myAdapterUser;
    ArrayList<UserView> list;
    Button btnPay;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, rname, rphone,remail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        recyclerView = findViewById(R.id.userList);
        p_ref = FirebaseDatabase.getInstance().getReference().child("PoojaList");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapterUser = new MyAdapterUser(this, list);
        recyclerView.setAdapter(myAdapterUser);

        p_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserView userView = dataSnapshot.getValue(UserView.class);
                    list.add(userView);
                }

                myAdapterUser.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference dref = fStore.collection("Users").document(userId);
        dref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 rname = value.getString("FullName");
                 remail = value.getString("UserEmail");
                 rphone = value.getString("PhoneNumber");
                //reg_name.setText(rname);
                //email.setText(remail);
                //phone.setText(rphone);
                //Intent intent = new Intent(ShowActivityUser.this, PoojaRegister.class);

            }
        });

        myAdapterUser.OnRecyclerViewClickListener(new MyAdapterUser.OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                //Toast.makeText(ShowActivityUser.this,"Position:" + position, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(ShowActivityUser.this, PoojaRegister.class);
                in.putExtra("nameINTENT", list.get(position).getName());
                in.putExtra("priceINTENT", list.get(position).getPrice());
                in.putExtra("dateINTENT", list.get(position).getDate());
                in.putExtra("descINTENT", list.get(position).getDesc());
                in.putExtra("userNameIntent", rname);
                in.putExtra("userPhoneIntent", rphone);
                startActivity(in);

            }
        });
    }
}


