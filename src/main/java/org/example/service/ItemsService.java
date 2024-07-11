package org.example.service;

import org.example.dao.ItemsManager;
import org.example.dto.Items;

import java.util.List;
import java.util.UUID;


public class ItemsService {
    private ItemsManager itemsManager = new ItemsManager();


    public Items saveItem(Items item) {
        item.setUuid(UUID.randomUUID().toString());
        itemsManager.saveItem(item);
        return getItem(item.getUuid());
    }

    public Items getItem(String uuid){
        return itemsManager.getItem(uuid);
    }

    public List<Items> getItems() {
        return itemsManager.getItems();
    }

    public void updateItem(Items item) {
        itemsManager.updateItem(item);
        //return getItem(item.getUuid());
    }

    public void deleteItem(String uuid) {
        itemsManager.deleteItem(uuid);
    }
}
