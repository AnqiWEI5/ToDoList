package com.example.td_wang_yang_wei.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"id"})},tableName = "users")
public class User {



    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "pseudo")
    public String pseudo;

    @ColumnInfo(name = "motDePasse")
    public String motDePasse;



    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPseudo(){
        return pseudo;
    }

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    public String getMotDePasse(){
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }



}


