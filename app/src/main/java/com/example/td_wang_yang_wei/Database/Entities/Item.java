package com.example.td_wang_yang_wei.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "items",
        foreignKeys = @ForeignKey(entity = List.class,
        parentColumns = "id",
        childColumns = "list_id",
        onDelete = CASCADE))
public class Item {
    @PrimaryKey
    @NonNull
    public String id;

    public String label;

    public String url;

    public String checked;

    @ColumnInfo(name = "list_id")
    public String listId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl() {
        this.url = url;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getListId() {
        return listId;
    }

    public void setListId() {
        this.listId = listId;
    }
}
