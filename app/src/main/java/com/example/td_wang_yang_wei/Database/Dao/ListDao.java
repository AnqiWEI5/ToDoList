package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.Entities.List;


@Dao
public interface ListDao {

    @Query("SELECT * FROM lists")
    java.util.List<List> getAllLists();

    @Query("SELECT * FROM lists WHERE user_id LIKE:userId")
    java.util.List<List> findListByUserId(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(java.util.List<List> listsLoad);

    @Delete
    void deleteList(List list);

    //TODO:根据需求补全



}
