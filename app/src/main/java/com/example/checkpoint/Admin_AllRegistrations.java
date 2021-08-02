package com.example.checkpoint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.app.SearchManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_AllRegistrations extends AppCompatActivity {
    FirebaseFirestore db;
    ListView listView;
    TextView listcount;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_registrations);

        searchView = (SearchView)findViewById(R.id.search_bar);
        listcount = (TextView)findViewById(R.id.count);
        db = FirebaseFirestore.getInstance();
        listView = (ListView)findViewById(R.id.All_listView);
        ArrayList<ModelAllReg> list = new ArrayList<>();
        NewAdapter adapter = new NewAdapter(this, R.layout.item_allregis, list);
        listView.setAdapter(adapter);
        CollectionReference dref = db.collection("AllRegistrations");
        dref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> dlist = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : dlist) {

                                ModelAllReg p = d.toObject(ModelAllReg.class);
                                p.setId(d.getId());
                                list.add(p);
                            }
                            adapter.notifyDataSetChanged();
                            //listcount.setText("Total count : " + listView.getCount());
                        }
                    }
                });
    //Search bar operation set filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().toString();
                return false;
            }
        });
    }
}