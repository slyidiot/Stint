package com.inskade.stint.database.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.inskade.stint.database.model.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(Item item);

    @Query("select * from item")
    public List<Item> getAllItems();

    @Query("select * from item where itemCollectionID = :itemCollectionID")
    public List<Item> getItemsByID(String itemCollectionID);

    @Query("select * from item where paid = 1 and delivered = 0 and itemCollectionID = :itemCollectionID")
    public List<Item> getPaidAndNotDeliveredItems(String itemCollectionID);

    @Query("select * from item where paid = 1 and itemCollectionID = :itemCollectionID")
    public List<Item> getPaidItems(String itemCollectionID);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(Item item);

    @Query("delete from item")
    void removeAllItems();

    @Query("delete from item where itemID = :itemID")
    void removeItem(String itemID);
}
