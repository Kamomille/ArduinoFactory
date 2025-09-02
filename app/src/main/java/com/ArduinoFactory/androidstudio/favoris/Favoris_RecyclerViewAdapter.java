package com.ArduinoFactory.androidstudio.favoris;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ArduinoFactory.androidstudio.R;

import java.util.ArrayList;



public class Favoris_RecyclerViewAdapter extends RecyclerView.Adapter<com.ArduinoFactory.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Favoris_Data> data;

    public Favoris_RecyclerViewAdapter(ArrayList<Favoris_Data> courseModalArrayList) {
        this.data = courseModalArrayList;
    }


    @NonNull
    @Override
    public com.ArduinoFactory.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.ArduinoFactory.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder holder, int position) {
        Favoris_Data modal = data.get(position);
        holder.name.setText(modal.getName());
        holder.image.setImageResource(modal.getImageId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}