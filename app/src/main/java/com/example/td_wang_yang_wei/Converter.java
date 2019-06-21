package com.example.td_wang_yang_wei;

import java.util.ArrayList;

import com.example.td_wang_yang_wei.Database.Entities.Item;
import com.example.td_wang_yang_wei.Database.Entities.List;
import com.example.td_wang_yang_wei.api.Items;
import com.example.td_wang_yang_wei.api.Lists;

public class Converter {

    public List listfrom(Lists.ListsBean listsBean) {
        List list = new List();
        list.setId(listsBean.getId());
        list.setLabel(listsBean.getLabel());
        return list;
    }

    public java.util.List<List> listsfrom(Lists lists) {
        java.util.List<List> listsSave = new ArrayList<>(lists.getLists().size());
        for (Lists.ListsBean listsBean : lists.getLists()) {
            listsSave.add(listfrom(listsBean));
        }
        return listsSave;
    }

    public Item itemfrom(Items.ItemsBean itemsBean) {
        Item item = new Item();
        item.setId(itemsBean.getId());
        item.setLabel(itemsBean.getLabel());
        item.setChecked(itemsBean.getChecked());

        return item;
    }

    public java.util.List<Item> itemsfrom(Items items) {
        java.util.List<Item> itemsSave = new ArrayList<>(items.getItems().size());
        for (Items.ItemsBean itemsBean : items.getItems()) {
            itemsSave.add(itemfrom(itemsBean));
        }
        return itemsSave;
    }
}