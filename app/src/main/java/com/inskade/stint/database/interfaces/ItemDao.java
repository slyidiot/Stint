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

    @Query("select * from item where itemID = :itemID")
    public List<Item> getItemsByID(long itemID);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(Item item);

    @Query("delete from item")
    void removeAllItems();
}
