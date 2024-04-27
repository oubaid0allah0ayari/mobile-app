package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class itemViewHolder extends RecyclerView.ViewHolder {
    public TextView  date ,location ,description , title;
    public itemViewHolder(@NonNull View itemView){
        super(itemView);
        date=itemView.findViewById(R.id.event_date_txt);
        location= itemView.findViewById(R.id.event_location_txt);
        description= itemView.findViewById(R.id.event_description_txt);
        title=itemView.findViewById(R.id.event_title_txt);
    }


}
