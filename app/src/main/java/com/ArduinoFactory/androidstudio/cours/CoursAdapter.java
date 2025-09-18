package com.ArduinoFactory.androidstudio.cours;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ArduinoFactory.androidstudio.R;
import java.util.ArrayList;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursViewHolder> {

    private final ArrayList<CoursData> list;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CoursData item);
    }

    public CoursAdapter(ArrayList<CoursData> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cours, parent, false);
        return new CoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursViewHolder holder, int position) {
        CoursData item = list.get(position);
        holder.text.setText(item.getName());
        holder.image.setImageResource(item.getImageRes());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CoursViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public CoursViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageCours);
            text = itemView.findViewById(R.id.nomCours);
        }
    }
}