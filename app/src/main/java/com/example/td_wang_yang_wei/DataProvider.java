package com.example.td_wang_yang_wei;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.UiThread;

import com.example.td_wang_yang_wei.Database.AppDatabase;
import com.example.td_wang_yang_wei.Database.Dao.ItemDao;
import com.example.td_wang_yang_wei.Database.Dao.ListDao;
import com.example.td_wang_yang_wei.Database.Dao.UserDao;
import com.example.td_wang_yang_wei.Database.Entities.Item;
import com.example.td_wang_yang_wei.Database.Entities.Liste;
import com.example.td_wang_yang_wei.Database.Entities.User;
import com.example.td_wang_yang_wei.api.Contenu;
import com.example.td_wang_yang_wei.api.Lists;
import com.example.td_wang_yang_wei.api.Utilisateur;
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

    //autenticate
    public Utilisateur authenticate(final String username,final String password,final authenticateListener listener) throws IOException {
        final Utilisateur[] utilisateur=new Utilisateur[1];
        Future future=Utils.BACKGROUND.submit((new Runnable() {

            @Override
            public void run() {
                try {
                    Response<Contenu> response=service.authenticate(username,password).execute();
                    if(response.isSuccessful()){
                        utilisateur[0] = new Utilisateur(username,password,response.body().hash);
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onSuccess(utilisateur[0]);
                            }
                        });
                    }
                    else {
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onError();
                            }
                        });
                        utilisateur[0] =null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

//        Response<Contenu> response=service.authenticate(username,password).execute();
//        if(response.isSuccessful()){
//            utilisateur = new Utilisateur(username,password,response.body().hash);
//                    }
//        else utilisateur=null;
            return utilisateur[0];
    }


    //executor pour le tableau List
    public void syncLists(final String hash,final ListListener listener){
        Future future = Utils.BACKGROUND.submit(new Runnable() {
            @Override public void run() {
                try {
                    Response<Lists> response = service.getLists(hash).execute();
                    if( response.isSuccessful()) {
                        final List<Liste> liste = converter.listefrom(response.body().getLists());
                        for(Liste i:liste){
                            listDao.insertAllListe(i);
                        }
                        uiHandler.post(new Runnable() {
                            @Override public void run() {
                                listener.onSuccess(liste);
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

    public List<Liste>loadListe(){
        return listDao.getAllLists();
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

    public interface ListListener{
        @UiThread
        public void onSuccess(List<Liste> lists);
        @UiThread
        public void onError();
    }


    public interface itemListener{
        @UiThread
        public void onSuccess(List<Item> items);
        @UiThread
        public void onError();
    }
    public interface authenticateListener{
        @UiThread
        public void onSuccess(Utilisateur utilisateur);
        @UiThread
        public void onError();
    }
}
