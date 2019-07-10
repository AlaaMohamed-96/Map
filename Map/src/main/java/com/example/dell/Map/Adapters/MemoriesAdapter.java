package com.example.dell.projectx.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.projectx.R;
import com.example.dell.projectx.model.Memory;

import java.util.List;

public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.ViewHolder> {
    List<Memory>memories;

    public MemoriesAdapter(List<Memory> memories) {
        this.memories = memories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_memory_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        Memory memory = memories.get(pos);
        viewHolder.title.setText(memory.getDescription());
        viewHolder.data.setText(memory.getData());

    }



    @Override
    public int getItemCount() {
        return memories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_item);
            data = itemView.findViewById(R.id.date_item);
        }
    }
}
