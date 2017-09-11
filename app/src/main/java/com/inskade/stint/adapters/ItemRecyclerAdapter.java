package com.inskade.stint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inskade.stint.R;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.viewholders.ItemViewHolder;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private ArrayList<Item> items;
    private Context context;

    public ItemRecyclerAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public void setItems(ArrayList<Item> entities) {
        if(this.items != entities) {
            this.items = entities;
            notifyDataSetChanged();
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_item, parent, false);
        return new ItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = this.items.get(position);
        holder.bindEntity(item, position);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public Item getItem(int position) {
        return this.items.get(position);
    }
}
