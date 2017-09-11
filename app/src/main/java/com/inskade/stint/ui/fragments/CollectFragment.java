package com.inskade.stint.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inskade.stint.R;
import com.inskade.stint.adapters.CollectPagerAdapter;
import com.inskade.stint.database.AppDatabase;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.Random;

public class CollectFragment extends Fragment{

    private View convertView;

    private ArrayList<ItemCollection> itemCollections;
    private RecyclerViewPager recyclerViewPager;
    private CollectPagerAdapter collectPagerAdapter;

    private AppDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        convertView = inflater.inflate(R.layout.fragment_collect, container, false);

        database = AppDatabase.getDatabase(getActivity().getApplicationContext());

        itemCollections = (ArrayList<ItemCollection>) database.itemCollectionModel().getAllItemCollections();

        findViews();
        setupRecyclerViewPager();

        return convertView;
    }

    private void setupRecyclerViewPager() {

        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPager.setLayoutManager(layout);

        collectPagerAdapter = new CollectPagerAdapter(itemCollections, getActivity());
        recyclerViewPager.setAdapter(collectPagerAdapter);
        recyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {

            }
        });
    }

    private void findViews() {
        recyclerViewPager = (RecyclerViewPager) convertView.findViewById(R.id.collect_viewpager);
    }
}