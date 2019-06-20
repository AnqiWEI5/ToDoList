package com.example.td_wang_yang_wei.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"id"})},tableName = "users")
public class User {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "pseudo")
    public String pseudo;

    @ColumnInfo(name = "motDePasse")
    public String motDePasse;

    public int getId(){
        return id;
    }

    public void setId(int id){
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


