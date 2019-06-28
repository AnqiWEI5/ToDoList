package com.example.td_wang_yang_wei.Database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.model.Listdb;

import java.util.List;

@Dao
public interface ListDao {

    @Query("SELECT * FROM lists")
    List<Listdb> getAllLists();

    @Query("SELECT * FROM lists WHERE user_id LIKE:userId")
    List<Listdb> findListByUserId(String userId);

    @Query("SELECT * FROM lists WHERE label LIKE:labelClick")
    Listdb findListByUserLabel(String labelClick);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Listdb> listsLoad);

    @Insert
    void add(Listdb listAdd);

    @Delete
    int deleteList(Listdb listdb);

    @Query("DELETE FROM lists")
    int deleteAll();

    //TODO:根据需求补全



}
