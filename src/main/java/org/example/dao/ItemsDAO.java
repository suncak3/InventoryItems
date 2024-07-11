package org.example.dao;

import org.example.dto.Items;

import java.sql.ResultSet;
import java.util.List;

public interface ItemsDAO {
    public void saveItem(Items item);
    public List<Items> getItems();
    public Items getItem(String uuid);
    public void updateItem(Items item);
    public void deleteItem(String uuid);
}
