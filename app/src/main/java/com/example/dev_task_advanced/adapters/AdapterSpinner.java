package com.example.dev_task_advanced.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dev_task_advanced.DTOs.CityDTO;
import com.example.dev_task_advanced.R;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {

    private Context context;
    private ArrayList<CityDTO> city ;
    private LayoutInflater layoutInflater;

    public AdapterSpinner ( Context context , ArrayList<CityDTO> city) {
        this.context = context;
        this.city = city;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return  city != null ?  city.size() : 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.spinner_item, viewGroup, false);

        TextView txtName = rootView.findViewById(R.id.name);

        txtName.setText(city.get(i).city);

        return rootView;
    }
}
