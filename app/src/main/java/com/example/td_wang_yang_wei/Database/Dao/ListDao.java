package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.Entities.List;

@Dao
public interface ListDao {

    @Query("SELECT * FROM lists")
    List[] getAllLists();

    @Query("SELECT * FROM lists WHERE user_id LIKE:userId")
    List[] findListByUserId(String userId);

    @Delete
    void deleteList(List list);

    //TODO:根据需求补全



}
