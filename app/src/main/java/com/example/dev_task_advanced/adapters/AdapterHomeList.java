package com.example.dev_task_advanced.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.R;

import java.util.ArrayList;

public class AdapterHomeList extends BaseAdapter {

    ArrayList<LocationDTO> locationById = null;
    Context context;
    LayoutInflater Inflater;


    public AdapterHomeList(Context ctx, ArrayList<LocationDTO> locationById){

        this.context = ctx;
        this.locationById = locationById;
        this.Inflater = LayoutInflater.from(ctx);
    }



    @Override
    public int getCount() {
        return locationById.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = Inflater.inflate(R.layout.home_fragment_list,null);
        TextView titleText = (TextView) convertView.findViewById(R.id.title_text);
        TextView adressText = (TextView) convertView.findViewById(R.id.address);

        titleText.setText(locationById.get(position).Name);
        adressText.setText(locationById.get(position).Location);

        return convertView;
    }
}
