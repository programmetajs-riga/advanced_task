package com.example.dev_task_advanced.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.databinding.HomeFragmentListBinding;

import java.util.ArrayList;

public class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.ViewHolder> {

    ArrayList<LocationDTO> locationById = null;
    LayoutInflater mInflater;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView address;

        public ViewHolder(HomeFragmentListBinding binding) {
            super(binding.getRoot());
            title = binding.titleText;
            address = binding.address;


        }
    }

    public AdapterHomeList(Context context , ArrayList<LocationDTO> locationById){
        this.locationById = locationById;
        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public AdapterHomeList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HomeFragmentListBinding binding = HomeFragmentListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdapterHomeList.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder binding, int position) {
        binding.title.setText(locationById.get(position).Name);
       // binding.address.setText(locationById.get(position).Location);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return locationById.size();
    }

}
