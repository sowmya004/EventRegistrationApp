package com.example.checkpoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MyAdapterUser extends RecyclerView.Adapter<MyAdapterUser.MyViewHolder>{

    Context context;
    ArrayList<UserView> list;
    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener(OnRecyclerViewClickListener listener){
        this.listener= listener;
    }

    public MyAdapterUser(Context context, ArrayList<UserView> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
       return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterUser.MyViewHolder holder, int position) {
        UserView userview = list.get(position);
        holder.name.setText(userview.getName());
        holder.price.setText(userview.getPrice());
        holder.date.setText(userview.getDate());
        holder.desc.setText(userview.getDesc());
        holder.userview = userview;
        /*holder.btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context.getApplicationContext(), Payment.class);
                context.getApplicationContext().startActivity(in);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public UserView userview;
        TextView name, price, date, desc;

        public MyViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {

            super(itemView);
            name = itemView.findViewById(R.id.pooja_name);
            price = itemView.findViewById(R.id.pooja_price);
            date = itemView.findViewById(R.id.pooja_date);
            desc = itemView.findViewById(R.id.pooja_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!= null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
