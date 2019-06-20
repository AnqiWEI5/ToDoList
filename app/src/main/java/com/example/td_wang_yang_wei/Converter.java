package com.example.td_wang_yang_wei;

import com.example.td_wang_yang_wei.Database.Entities.Item;
import com.example.td_wang_yang_wei.Database.Entities.Liste;
import com.example.td_wang_yang_wei.Database.Entities.User;
import com.example.td_wang_yang_wei.api.Items;
import com.example.td_wang_yang_wei.api.Lists;
import com.example.td_wang_yang_wei.api.Users;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public User from(Users.UsersBean userBean) {
        User user = new User();
        user.setId(userBean.getId());
        user.setPseudo(userBean.getPseudo());
        //user.setMotDePasse(userBean.getMotDePasse());

        return user;
    }

    /**
    public List<User> from(List<Users.UsersBean> usersBean) {
        List<User> users = new ArrayList<>(usersBean.size());
        for (Users.UsersBean userBean  : usersBean) {
            users.add(from(userBean));
        }
        return users;
    }*/

    //List
    public Liste from(Lists.ListsBean listBean) {
        Liste liste = new Liste();
        liste.setId(listBean.getId());
        liste.setLabel(listBean.getLabel());
        //user.setMotDePasse(userBean.getMotDePasse());

        return liste;
    }

    public List<Liste> from(List<Lists.ListsBean> listesBean) {
        List<Liste> listes = new ArrayList<>(listesBean.size());
        for (Lists.ListsBean listeBean  : listesBean) {
            listes.add(from(listeBean));
        }
        return listes;
    }
    //Item
    public Item from(Items.ItemsBean itemBean) {
        Item item = new Item();
        item.setId(itemBean.getId());
        item.setLabel(itemBean.getLabel());
        //user.setMotDePasse(userBean.getMotDePasse());

        return item;
    }

    public List<Item> from(List<Items.ItemsBean> itemsBean) {
        List<Item> items = new ArrayList<>(itemsBean.size());
        for (Items.ItemsBean itemBean  : itemsBean) {
            items.add(from(itemBean));
        }
        return items;
    }

}
