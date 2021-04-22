package com.example.androidstudio.achat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudio.R;

public class View_Holder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView imageView;

    View_Holder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }
}