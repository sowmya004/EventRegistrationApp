package com.example.checkpoint;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;
    private View.OnClickListener mListener;
    FirebaseDatabase fbase = FirebaseDatabase.getInstance();


    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());
        holder.desc.setText(model.getDesc());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position, model.getName());
            }
        });
    }


    private void delete(int position, String name) {
        DatabaseReference root = fbase.getReference().child("PoojaList").child(name);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(context, ShowActivity.class);
                context.startActivity(in);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
   }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, date, desc;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pooja_name);
            price = itemView.findViewById(R.id.pooja_price);
            date = itemView.findViewById(R.id.pooja_date);
            desc = itemView.findViewById(R.id.pooja_desc);
            delete = itemView.findViewById(R.id.btn_delete);

        }
    }}


