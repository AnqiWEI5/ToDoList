package com.example.td_wang_yang_wei.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "items",
        foreignKeys = @ForeignKey(entity = Liste.class,
        parentColumns = "id",
        childColumns = "liste_id",
        onDelete = CASCADE))
public class Item {
    @PrimaryKey
    public String id;

    public String label;

    public Object url;

    public String checked;

    @ColumnInfo(name = "liste_id")
    public String listeId;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLabel() {
//        return label;
//    }
//
//    public void setLabel() {
//        this.label = label;
//    }
//
//    public Object getUrl() {
//        return url;
//    }
//
//    public void setUrl() {
//        this.url = url;
//    }
//
//    public String getChecked() {
//        return checked;
//    }
//
//    public void setChecked(String checked) {
//        this.checked = checked;
//    }
//
//    public String getListId() {
//        return listId;
//    }
//
//    public void setListId() {
//        this.listId = listId;
//    }
}
