package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class adapter extends RecyclerView.Adapter<itemViewHolder> {

    Context context;
    List<Event> items;

    public adapter(Context context, List<Event> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        Event event = items.get(position);
        holder.date.setText(event.getDate());
        holder.description.setText(event.getDescription());
        holder.title.setText(event.getTitle());
        holder.location.setText(event.getLocation());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

