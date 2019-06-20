package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.Entities.Liste;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ListDao {

    @Query("SELECT * FROM listes")
    List<Liste> getAllLists();

    @Query("SELECT * FROM listes WHERE user_id LIKE:userId")
    List<Liste> findListByUserId(String userId);

    @Insert(onConflict = REPLACE)
    void insertAllListe(Liste...listes);

    @Delete
    void deleteListe(Liste liste);

    //TODO:根据需求补全



}
