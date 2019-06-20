package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.td_wang_yang_wei.Database.Entities.Item;
import com.example.td_wang_yang_wei.Database.Entities.Liste;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items")
    List<Item> getAllItems();

    @Query("SELECT * FROM items WHERE liste_id LIKE:listeId")
    List<Item> findItemByListeId(String listeId);

    @Insert(onConflict = REPLACE)
    void insertAllItem(Item...items);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    //TODO:根据需求补全
}
