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
import com.inskade.stint.adapters.CollectPagerAdapter;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;
import com.inskade.stint.ui.activities.MainActivity;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CollectFragment extends Fragment{

    private View convertView;

    private ArrayList<ItemCollection> itemCollections;
    public RecyclerViewPager recyclerViewPager;
    private CollectPagerAdapter collectPagerAdapter;

    public static CollectFragment instance;

    public static CollectFragment getInstance() {
        if(instance == null) {
            instance = new CollectFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        convertView = inflater.inflate(R.layout.fragment_collect, container, false);
        instance = this;

        findViews();
        setupRecyclerViewPager();

        return convertView;
    }

    private void setupRecyclerViewPager() {

        itemCollections = (ArrayList<ItemCollection>) Stint.getInstance().database.itemCollectionModel().getAllItemCollections();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPager.setLayoutManager(layout);
        recyclerViewPager.setNestedScrollingEnabled(false);

        collectPagerAdapter = new CollectPagerAdapter(itemCollections, getActivity());
        recyclerViewPager.setAdapter(collectPagerAdapter);
        recyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {
                updateCostTextViews(i1);
            }
        });

        try {
            updateCostTextViews(recyclerViewPager.getCurrentPosition());
        } catch (Exception e) {
            MainActivity.getInstance().singleCost.setText(getString(R.string.default_single_cost_text));
            MainActivity.getInstance().totalCost.setText(getString(R.string.default_total_cost_text));
        }
    }

    public void updateCostTextViews(int position) {
        float singleCost = collectPagerAdapter.getItem(position).individualCost;
        MainActivity.getInstance().singleCost.setText("₹"+singleCost);

        ArrayList<Item> paidItems = (ArrayList<Item>) Stint.getInstance().database.itemModel().getPaidItems(collectPagerAdapter.getItem(position).id);
        int x = paidItems.size();
        float totalCost = (singleCost*x);
        MainActivity.getInstance().totalCost.setText("Total: ₹"+totalCost);
    }

    public void refreshDataset(){
        itemCollections = (ArrayList<ItemCollection>) Stint.getInstance().database.itemCollectionModel().getAllItemCollections();
        if(itemCollections.isEmpty()) {
            MainActivity.getInstance().singleCost.setText(getString(R.string.default_single_cost_text));
            MainActivity.getInstance().totalCost.setText(getString(R.string.default_total_cost_text));
        }
        recyclerViewPager.post(new Runnable() {
            @Override
            public void run() {
                collectPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void findViews() {
        recyclerViewPager = (RecyclerViewPager) convertView.findViewById(R.id.collect_viewpager);
    }
}