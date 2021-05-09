package com.example.androidstudio.favoris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudio.R;
import com.example.androidstudio.achat.Achat_Data;

import java.util.ArrayList;



public class Favoris_RecyclerViewAdapter extends RecyclerView.Adapter<com.example.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Favoris_Data> data;
    private Context context;

    public Favoris_RecyclerViewAdapter(ArrayList<Favoris_Data> courseModalArrayList, Context context) {
        this.data = courseModalArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public com.example.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new com.example.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.androidstudio.favoris.Favoris_RecyclerViewAdapter.ViewHolder holder, int position) {
        Favoris_Data modal = data.get(position);
        holder.name.setText(modal.getName());
        holder.image.setImageResource(modal.getImageId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}