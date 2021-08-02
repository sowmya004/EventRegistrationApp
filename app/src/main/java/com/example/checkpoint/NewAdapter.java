package com.example.checkpoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends ArrayAdapter<ModelAllReg> {
    Context mContext;
    int mResource;

    public NewAdapter(Context context,int resource, ArrayList<ModelAllReg> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String poojaname= getItem(position).getName();
        String poojadate = getItem(position).getDate();
        String personname = getItem(position).getUserName();
        String personphone = getItem(position).getUserPhone();

        ModelAllReg modelAllReg = new ModelAllReg(poojaname,poojadate,personname,personphone);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView lvpname = (TextView)convertView.findViewById(R.id.lv_pn);
        TextView lvpdate = (TextView)convertView.findViewById(R.id.lv_pd);
        TextView lvname = (TextView)convertView.findViewById(R.id.lv_un);
        TextView lvphone = (TextView)convertView.findViewById(R.id.lv_up);

        lvpname.setText(poojaname);
        lvpdate.setText(poojadate);
        lvname.setText(personname);
        lvphone.setText(personphone);

        return convertView;
    }
}
