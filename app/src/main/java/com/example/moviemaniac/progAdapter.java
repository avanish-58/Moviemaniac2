package com.example.moviemaniac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class progAdapter extends RecyclerView.Adapter<progAdapter.progViewholder>{
    private String[] data;
    public progAdapter(String[] data)
    {
        this.data=data;
    }
    @NonNull
    @Override
    public progViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.list_layout,parent,false);
        return new progViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull progViewholder holder, int position) {
String title=data[position];
holder.moviename.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class progViewholder extends RecyclerView.ViewHolder{
        TextView moviename;
        public progViewholder(@NonNull View itemView) {
            super(itemView);
            moviename=(TextView) itemView.findViewById(R.id,moviename);
        }
    }
}