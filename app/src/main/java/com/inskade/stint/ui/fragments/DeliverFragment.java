package com.inskade.stint.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inskade.stint.R;
import com.inskade.stint.Stint;
import com.inskade.stint.adapters.DeliverPagerAdapter;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;
import com.inskade.stint.ui.activities.MainActivity;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

public class DeliverFragment extends Fragment{

    private View convertView;

    private ArrayList<ItemCollection> itemCollections;
    public RecyclerViewPager recyclerViewPager;
    private DeliverPagerAdapter deliverPagerAdapter;

    public static DeliverFragment instance;

    public static DeliverFragment getInstance() {
        if(instance == null) {
            instance = new DeliverFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        convertView = inflater.inflate(R.layout.fragment_collect, container, false);
        instance = this;
        itemCollections = (ArrayList<ItemCollection>) Stint.getInstance().database.itemCollectionModel().getDeliverReadyItemCollections();
        if(itemCollections.isEmpty())
            Log.d("ItemCollections", "isEmpty");

        findViews();
        setupRecyclerViewPager();

        return convertView;
    }

    private void setupRecyclerViewPager() {

        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPager.setLayoutManager(layout);
        recyclerViewPager.setNestedScrollingEnabled(false);

        deliverPagerAdapter = new DeliverPagerAdapter(itemCollections, getActivity());
        recyclerViewPager.setAdapter(deliverPagerAdapter);
        recyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {
                updateCountTextViews(i1);
            }
        });
        try {
            updateCountTextViews(recyclerViewPager.getCurrentPosition());
        } catch (Exception e) {
            MainActivity.getInstance().singleCost.setText("0");
            MainActivity.getInstance().totalCost.setText("Remaining");
        }
    }


    public void updateCountTextViews(int position) {
        ArrayList<Item> paidItems = (ArrayList<Item>) Stint.getInstance().database.itemModel().getPaidAndNotDeliveredItems(deliverPagerAdapter.getItem(position).id);
        int x = paidItems.size();
        MainActivity.getInstance().singleCost.setText(""+x);
        MainActivity.getInstance().totalCost.setText("Remaining");
    }

    public void refreshDataset(){
        itemCollections = (ArrayList<ItemCollection>) Stint.getInstance().database.itemCollectionModel().getDeliverReadyItemCollections();
        if(itemCollections.isEmpty()) {
            MainActivity.getInstance().singleCost.setText("0");
            MainActivity.getInstance().totalCost.setText("Remaining");
        }
        try {
            updateCountTextViews(recyclerViewPager.getCurrentPosition());
        } catch (Exception e) {
            //do nothing
        }
        recyclerViewPager.post(new Runnable() {
            @Override
            public void run() {
                deliverPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void findViews() {
        recyclerViewPager = (RecyclerViewPager) convertView.findViewById(R.id.collect_viewpager);
    }
}