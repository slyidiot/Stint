package com.inskade.stint.viewholders;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inskade.stint.R;
import com.inskade.stint.Stint;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;
import com.inskade.stint.ui.fragments.CollectFragment;
import com.inskade.stint.ui.fragments.DeliverFragment;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    private Context context;

    private int position;
    private int fragmentCode;
    private boolean doubleTapPressecOnce = false;

    private Toast toast;

    private Item item;

    private TextView name;
    private ImageView paidStatus;
    private View itemView;

    private Typeface covesBold;

    public ItemViewHolder(View itemView, Context context, int fragmentCode) {
        super(itemView);
        this.context = context;

        this.fragmentCode = fragmentCode;
        this.name = itemView.findViewById(R.id.entity_name);
        this.paidStatus = itemView.findViewById(R.id.paid_status_imageview);
        this.itemView = itemView;
    }

    public void bindEntity(final Item item, int position) {
        this.position = position;
        this.item = item;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentCode == Stint.COLLECT_FRAGMENT) {
                    if (item.paid) {
                        item.paid = false;
                        item.delivered = false;
                    } else {
                        item.paid = true;
                        item.delivered = false;
                    }
                    Stint.getInstance().database.itemModel().updateItem(item);
                    Stint.getInstance().updateDeliverStatus(item.itemCollectionID);
                    updateItemPaidStatus(item);
                    CollectFragment.getInstance().updateCostTextViews(CollectFragment.getInstance().recyclerViewPager.getCurrentPosition());
                    CollectFragment.getInstance().refreshDataset();
                } else if(fragmentCode == Stint.DELIVER_FRAGMENT) {
                    if (doubleTapPressecOnce) {
                        item.delivered = true;
                        Stint.getInstance().database.itemModel().updateItem(item);
                        if(Stint.getInstance().database.itemModel().getPaidAndNotDeliveredItems(item.itemCollectionID).isEmpty()) {
                            ItemCollection itemCollection = Stint.getInstance().database.itemCollectionModel().getSingleItemCollection(item.itemCollectionID);
                            itemCollection.deliverStatus = false;
                            Stint.getInstance().database.itemCollectionModel().updateItemCollection(itemCollection);
                        }
                        DeliverFragment.getInstance().refreshDataset();
                        CollectFragment.getInstance().refreshDataset();
                        if (toast != null)
                            toast.cancel();
                    } else {
                        doubleTapPressecOnce = true;
                        if (toast != null)
                            toast.cancel();
                        toast = Toast.makeText(context, "Tap again to confirm delivery", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleTapPressecOnce = false;
                        }
                    }, 2000);
                }
            }
        });
        name.setText(item.name);
        updateItemPaidStatus(item);
    }
    private void updateItemPaidStatus(Item item) {
        if(item.paid)
            paidStatus.setImageResource(R.drawable.paid_drawable);
        else
            paidStatus.setImageResource(R.drawable.not_paid_drawable);
    }
}
