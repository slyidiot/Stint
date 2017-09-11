package com.inskade.stint.viewholders;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inskade.stint.R;
import com.inskade.stint.database.model.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private int position;

    private Item item;

    private TextView name;
    private ImageView paidStatus;

    private Typeface covesBold;

    public ItemViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;

        this.name = itemView.findViewById(R.id.entity_name);
        this.paidStatus = itemView.findViewById(R.id.paid_status_imageview);
    }

    public void bindEntity(Item item, int position) {
        this.position = position;
        this.item = item;

        name.setText(item.name);
        if(item.paid)
            paidStatus.setBackgroundColor(context.getResources().getColor(R.color.paid));
        else
            paidStatus.setBackgroundColor(context.getResources().getColor(R.color.not_paid));
    }
}
