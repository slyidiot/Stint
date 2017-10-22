package com.inskade.stint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inskade.stint.R;
import com.inskade.stint.database.model.ItemCollection;
import com.inskade.stint.viewholders.CollectViewHolder;
import com.inskade.stint.viewholders.DeliverViewHolder;

import java.util.ArrayList;

public class DeliverPagerAdapter extends RecyclerView.Adapter<DeliverViewHolder>{

    private ArrayList<ItemCollection> itemCollections;
    private Context context;

    public DeliverPagerAdapter(ArrayList<ItemCollection> itemCollections, Context context) {
        this.itemCollections = itemCollections;
        this.context = context;
    }

    @Override
    public DeliverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_item, parent, false);
        return new DeliverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeliverViewHolder holder, int position) {
        ItemCollection itemCollection = this.itemCollections.get(position);
        holder.bindItem(itemCollection, position);
    }

    @Override
    public int getItemCount() {
        return this.itemCollections.size();
    }

    public ItemCollection getItem(int position) {
        try {
            return this.itemCollections.get(position);
        } catch (Exception e) {
            //do nothing
        }
        return null;
    }
}
