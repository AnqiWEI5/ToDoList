package com.example.td_wang_yang_wei;

import com.example.td_wang_yang_wei.Database.Entities.User;
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

    public List<User> from(List<Users.UsersBean> usersBean) {
        List<User> users = new ArrayList<>(usersBean.size());
        for (Users.UsersBean userBean  : usersBean) {
            users.add(from(userBean));
        }
        return users;
    }

}
