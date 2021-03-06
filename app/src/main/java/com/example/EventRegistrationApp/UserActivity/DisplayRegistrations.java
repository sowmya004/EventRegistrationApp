package com.example.EventRegistrationApp.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.EventRegistrationApp.AdapterClasses.MyAdapterDisplayRegistrations;
import com.example.EventRegistrationApp.ModelClasses.ModelDisplayReg;
import com.example.EventRegistrationApp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DisplayRegistrations extends AppCompatActivity {

    FirebaseAuth fAuth;
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private MyAdapterDisplayRegistrations adapter;
    private List<ModelDisplayReg> list;
    Button reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_registrations);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new MyAdapterDisplayRegistrations(this, list);

        fAuth = FirebaseAuth.getInstance();
        recyclerView.setAdapter(adapter);
        String userId = fAuth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        DocumentReference dref = db.collection("Users").document(userId);

        dref.collection("Registrations").orderBy("date").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> dlist = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : dlist) {

                                ModelDisplayReg p = d.toObject(ModelDisplayReg.class);
                                p.setId(d.getId());
                                list.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
