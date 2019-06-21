package com.example.td_wang_yang_wei;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.UiThread;

import com.example.td_wang_yang_wei.DataClass.ListeDeUtilisateur;
import com.example.td_wang_yang_wei.Database.AppDatabase;
import com.example.td_wang_yang_wei.Database.Dao.ListDao;
import com.example.td_wang_yang_wei.Database.Dao.UserDao;
import com.example.td_wang_yang_wei.api.Lists;
import com.example.td_wang_yang_wei.api.requestService;
import com.example.td_wang_yang_wei.api.requestServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import retrofit2.Response;

public class DataProvider {

    private List<Future> futures = new ArrayList<>();
    private final Handler uiHandler;
    private final UserDao userDao;
    private final ListDao listDao;
    //TODO:实现url替换（现在用的默认）
    private final requestService service = requestServiceFactory.createService(ListeDeUtilisateur.getUrlDefault(),requestService.class);
    private final Converter converter = new Converter();


    public DataProvider(Context context){
        uiHandler = new Handler(context.getMainLooper());
        userDao = AppDatabase.getDatabase(context).userDao();
        listDao = AppDatabase.getDatabase(context).listDao();
    }

    public void syncLists(final String hash, final ListsListener listener) {

        Future future = Utils.BACKGROUND.submit(new Runnable() {
            @Override public void run() {
                try {
                    Response<Lists> response = service.getLists(hash).execute();
                    if( response.isSuccessful()) {
                        final List<com.example.td_wang_yang_wei.Database.Entities.List> listsSave = converter.listsfrom(response.body());
                        listDao.save(listsSave);
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onSuccess(listsSave);
                            }
                        });

                    }else {
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onError();
                            }
                        });

                    }

                } catch (IOException e) {
                    uiHandler.post(new Runnable() {
                        @Override public void run() {
                            listener.onError();
                        }
                    });
                }
            }
        });
        futures.add(future);

    }


    public interface ListsListener {
        @UiThread
        public void onSuccess(List<com.example.td_wang_yang_wei.Database.Entities.List> lists);
        @UiThread
        public void onError();
    }
}
