package com.example.td_wang_yang_wei;

import android.content.Context;

import com.example.td_wang_yang_wei.DataClass.Utilisateur;
import com.example.td_wang_yang_wei.Database.AppDatabase;
import com.example.td_wang_yang_wei.Database.Dao.ListDao;
import com.example.td_wang_yang_wei.Database.Dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Handler;

public class DataProvider {

    private List<Future> futures = new ArrayList<>();
    private final Handler uiHandler;
    private final UserDao userDao;
    private final ListDao listDao;


    public DataProvider(Context context){
        uiHandler = new Handler(context.getMainLooper());
        userDao = AppDatabase.getDatabase(context).userDao();
        listDao = AppDatabase.getDatabase(context).listDao();
    }

    public void syncUsers(final UsersListener listener){
        Future future = Uti
    }

    private interface UsersListener {
    }
}
