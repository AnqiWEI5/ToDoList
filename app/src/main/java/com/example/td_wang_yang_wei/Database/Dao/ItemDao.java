package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.td_wang_yang_wei.Database.Entities.Item;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items")
    Item[] getAllLists();

    @Query("SELECT * FROM items WHERE list_id LIKE:listId")
    Item[] findItemBylistId(String listId);

    @Insert
    void postItem(Item item);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    //TODO:根据需求补全
}