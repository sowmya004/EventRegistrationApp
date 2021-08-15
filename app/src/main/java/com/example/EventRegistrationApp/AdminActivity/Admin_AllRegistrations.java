package com.example.EventRegistrationApp.AdminActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.example.EventRegistrationApp.AdapterClasses.NewAdapter;
import com.example.EventRegistrationApp.ModelClasses.ModelAllReg;
import com.example.EventRegistrationApp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Admin_AllRegistrations extends AppCompatActivity {

    FirebaseFirestore db;
    ListView listView;
    TextView listcount;
    EditText searchView;
    private static final String TAG = "FirestoreSearchActivity";
    ArrayList<ModelAllReg> list;
    NewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_registrations);

        searchView = (EditText) findViewById(R.id.search_bar);
        listcount = (TextView) findViewById(R.id.count);
        db = FirebaseFirestore.getInstance();
        listView = (ListView) findViewById(R.id.All_listView);
        list = new ArrayList<>();
        adapter = new NewAdapter(this, R.layout.item_allregis, list);
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
                            listcount. setText("Total count : " + listView.getCount());
                        }
                    }
                });
            }


    private void searchData(String s) {
         db.collection("AllRegistrations").whereEqualTo("name", s)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot doc: task.getResult()){
                            ModelAllReg model = new ModelAllReg(
                                    doc.getString("name"),
                                    (String) doc.get("date"),
                            doc.getString("userName"),
                            doc.getString("userPhone"));
                            list.add(model);
                        }
                        listView.setAdapter(adapter);
                        listcount. setText("count : " + listView.getCount());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String date) {
                searchData(date);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}