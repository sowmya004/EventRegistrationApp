package com.example.EventRegistrationApp.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EventRegistrationApp.ModelClasses.ModelDisplayReg;
import com.example.EventRegistrationApp.UserActivity.Notification;
import com.example.EventRegistrationApp.R;

import java.util.List;

public class MyAdapterDisplayRegistrations  extends RecyclerView.Adapter<MyAdapterDisplayRegistrations.PoojaViewHolder>{
    private Context mCtx;
    private List<ModelDisplayReg> list;

    public MyAdapterDisplayRegistrations(Context mCtx, List<ModelDisplayReg> list)
    {
        this.mCtx = mCtx;
        this.list = list;
    }

    @NonNull

    @Override
    public PoojaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoojaViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.item_display_reg, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterDisplayRegistrations.PoojaViewHolder holder, int position) {
        ModelDisplayReg pooja = list.get(position);

        holder.reg_name.setText(pooja.getName());
        holder.reg_price.setText(pooja.getPrice());
        holder.reg_date.setText(pooja.getDate());
        holder.reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mCtx, Notification.class);
                in.putExtra("nameINTENT", pooja.getName());
                in.putExtra("dateINTENT", pooja.getDate());
                mCtx.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PoojaViewHolder extends RecyclerView.ViewHolder {
        private TextView reg_name, reg_price, reg_date;
        Button reminder;

        public PoojaViewHolder(@NonNull View itemView) {
            super(itemView);
            reg_name= itemView.findViewById(R.id.reg_name);
            reg_price= itemView.findViewById(R.id.reg_price);
            reg_date= itemView.findViewById(R.id.reg_date);
            reminder = itemView.findViewById(R.id.btn_reminder);



        }
    }
}
