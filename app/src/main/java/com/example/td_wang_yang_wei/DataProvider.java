package com.example.td_wang_yang_wei;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.UiThread;

import com.example.td_wang_yang_wei.Database.AppDatabase;
import com.example.td_wang_yang_wei.Database.Dao.ItemDao;
import com.example.td_wang_yang_wei.Database.Dao.ListDao;
import com.example.td_wang_yang_wei.Database.Dao.UserDao;
import com.example.td_wang_yang_wei.Database.Entities.User;
import com.example.td_wang_yang_wei.api.ListeDeUtilisateur;
import com.example.td_wang_yang_wei.api.Users;
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
    private final ItemDao itemDao;

    private String url;
    private final requestService service = requestServiceFactory.createService(url,requestService.class);
    private final Converter converter = new Converter();

    public DataProvider(Context context){
        uiHandler = new Handler(context.getMainLooper());
        userDao = AppDatabase.getDatabase(context).userDao();
        listDao = AppDatabase.getDatabase(context).listDao();
        itemDao = AppDatabase.getDatabase(context).itemDao();
    }

    //executor pour le tableau User
    public void syncUsers(final UsersListener listener){
        Future future = Utils.BACKGROUND.submit(new Runnable() {
            @Override public void run() {
                try {
                    String hash = "b10ab07311337e6484153b0f5793d516";
                    Response<Users> response = service.getUsers(hash).execute();
                    if( response.isSuccessful()) {
                        final List<User> users = converter.from(response.body().getUsers());
                        userDao.save(users);
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onSuccess(users);
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

    public void stop() {
        for (Future future : futures) {
            if(!future.isDone()){
                future.cancel(true);

            }
        }
        futures.clear();
    }

    public List<User> loadUser() {
        return userDao.getAllUsers();
    }

    public void save(List<User> userList) {
        userDao.save(userList);
    }

    private interface UsersListener {
        @UiThread
        public void onSuccess(List<User> users);
        @UiThread
        public void onError();
    }
}
