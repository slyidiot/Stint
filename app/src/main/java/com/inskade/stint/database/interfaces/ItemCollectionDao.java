package com.inskade.stint.database.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;

import java.util.List;

@Dao
public interface ItemCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItemCollection(ItemCollection itemCollection);

    @Query("select * from itemCollection")
    public List<ItemCollection> getAllItemCollections();

    @Query("select * from itemCollection where id = :id")
    public List<ItemCollection> getItemCollection(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemCollection(ItemCollection itemCollection);

    @Query("delete from itemCollection")
    void removeAllItemCollections();
}
