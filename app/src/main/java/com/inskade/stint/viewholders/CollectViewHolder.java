package com.inskade.stint.viewholders;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inskade.stint.R;
import com.inskade.stint.Stint;
import com.inskade.stint.adapters.ItemRecyclerAdapter;
import com.inskade.stint.database.model.ItemCollection;
import com.inskade.stint.misc.TypefaceCache;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.ui.activities.MainActivity;
import com.inskade.stint.ui.fragments.CollectFragment;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CollectViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private int position;
    private boolean allPaid = true;

    private ItemCollection itemCollection;

    private TextView name;
    private TextView nameHeader;
    private TextView paidHeader;
    private ImageView checkedOut;
    private RecyclerView recyclerView;

    private ItemRecyclerAdapter itemRecyclerAdapter;
    private ArrayList<Item> items;


    private Typeface covesBold;

    public CollectViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();

        this.name = (TextView) itemView.findViewById(R.id.item_name);
        this.recyclerView = (RecyclerView) itemView.findViewById(R.id.list_recycler_view);
        this.nameHeader = (TextView) itemView.findViewById(R.id.name_header);
        this.paidHeader = (TextView) itemView.findViewById(R.id.paid_header);
        this.checkedOut = (ImageView) itemView.findViewById(R.id.checked_out);

        items = new ArrayList<>();

        LinearLayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        itemRecyclerAdapter = new ItemRecyclerAdapter(items, context, Stint.COLLECT_FRAGMENT);
        recyclerView.setAdapter(itemRecyclerAdapter);

        covesBold = TypefaceCache.get(context.getAssets(), "CovesBold");
        name.setTypeface(covesBold);
        nameHeader.setTypeface(covesBold);
        paidHeader.setTypeface(covesBold);
    }

    public void bindItem(ItemCollection itemCollection, int position) {
        this.position = position;
        this.itemCollection = itemCollection;

        name.setText(itemCollection.itemName.toUpperCase());

        //TODO:Remove this
        items = (ArrayList<Item>) Stint.getInstance().database.itemModel().getItemsByID(itemCollection.id);
        itemRecyclerAdapter.setItems(items);
        if(items.isEmpty()) {
            Stint.getInstance().database.itemCollectionModel().removeItemCollection(itemCollection.id);
            CollectFragment.getInstance().refreshDataset();
        } else {
            for(Item item: items) {
                if(!item.paid) {
                    allPaid = false;
                    break;
                }
            }
            if(allPaid) {
                checkedOut.setVisibility(View.VISIBLE);
            } else {
                checkedOut.setVisibility(View.INVISIBLE);
            }
        }
    }
}
