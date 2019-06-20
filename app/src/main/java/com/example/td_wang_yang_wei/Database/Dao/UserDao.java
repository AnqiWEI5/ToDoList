package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.Entities.User;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE id IN(:userIds)")
    List<User> loadAllUsersByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE pseudo LIKE:pseudo")
    User findUserByPseudo(String pseudo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<User> users);

    @Delete
    void delete(User user);

    //TODO:根据需求补全


}
