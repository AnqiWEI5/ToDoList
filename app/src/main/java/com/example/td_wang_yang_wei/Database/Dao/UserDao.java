package com.example.td_wang_yang_wei.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.td_wang_yang_wei.Database.Entities.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    User[] getAllUsers();

    @Query("SELECT * FROM users WHERE id IN(:userIds)")
    User[] loadAllUsersByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE pseudo LIKE:pseudo")
    User[] findUserByPseudo(String pseudo);

    @Insert
    void postUsers(User users);

    //TODO:根据需求补全


}
