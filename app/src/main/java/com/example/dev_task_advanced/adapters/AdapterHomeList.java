package com.example.dev_task_advanced.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dev_task_advanced.DTOs.LocationDTO;
import com.example.dev_task_advanced.R;
import com.example.dev_task_advanced.databinding.HomeFragmentListBinding;

import java.util.ArrayList;

public class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.ViewHolder> {

    ArrayList<LocationDTO> locationById = null;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(HomeFragmentListBinding binding) {
            super(binding.getRoot());
        }
    }

    public AdapterHomeList(ArrayList<LocationDTO> locationById){
        this.locationById = locationById;
    }


    @NonNull
    @Override
    public AdapterHomeList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HomeFragmentListBinding binding = HomeFragmentListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdapterHomeList.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeList.ViewHolder holder, int position) {
        holder.locationById
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
