package com.inskade.stint;

import android.app.Application;

import com.inskade.stint.database.AppDatabase;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class Stint extends Application {

    public static final int COLLECT_FRAGMENT = 1;
    public static final int DELIVER_FRAGMENT = 2;

    public AppDatabase database;

    public static Stint instance;

    public static Stint getInstance() {
        if(instance==null) {
            instance = new Stint();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = AppDatabase.getDatabase(getApplicationContext());
    }

    public void updateDeliverStatus(String itemCollectionID) {
        ArrayList<Item> items = (ArrayList<Item>) database.itemModel().getPaidItems(itemCollectionID);
        if(!items.isEmpty()) {
            ItemCollection itemCollection = database.itemCollectionModel().getSingleItemCollection(itemCollectionID);
            itemCollection.deliverStatus = true;
            database.itemCollectionModel().updateItemCollection(itemCollection);
        } else {
            ItemCollection itemCollection = database.itemCollectionModel().getSingleItemCollection(itemCollectionID);
            itemCollection.deliverStatus = false;
            database.itemCollectionModel().updateItemCollection(itemCollection);
        }
    }
}
