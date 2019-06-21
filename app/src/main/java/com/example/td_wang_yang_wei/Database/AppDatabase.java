package com.example.td_wang_yang_wei.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.td_wang_yang_wei.Database.Dao.ItemDao;
import com.example.td_wang_yang_wei.Database.Dao.ListDao;
import com.example.td_wang_yang_wei.Database.Dao.UserDao;
import com.example.td_wang_yang_wei.Database.Entities.Item;
import com.example.td_wang_yang_wei.Database.Entities.Liste;
import com.example.td_wang_yang_wei.Database.Entities.User;

@Database(entities = {User.class, Liste.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ListDao listDao();
    public abstract ItemDao itemDao();

    private static AppDatabase db;

    public static AppDatabase getDatabase(final Context context){
        if (db == null) {
            synchronized(AppDatabase.class){
                if(db == null){
                    db = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                            "appdatabase").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return db;

    }
}
