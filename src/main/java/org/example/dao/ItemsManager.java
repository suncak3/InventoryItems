package org.example.dao;

import org.example.dto.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsManager implements ItemsDAO{
    @Override
    public void saveItem(Items item){
        String sql = "INSERT INTO items (uuid, price, name) VALUES (?, ?, ?)";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, item.getUuid());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Items> getItems(){
        List<Items> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Items item = new Items();
                item.setUuid(rs.getString("uuid"));
                item.setPrice(rs.getInt("price"));
                item.setName(rs.getString("name"));
                itemsList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public Items getItem(String uuid) {
        String sql = "SELECT * FROM items WHERE items.uuid = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);) {

            stmt.setString(1, uuid);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    Items item = new Items();
                    item.setUuid(rs.getString("uuid"));
                    item.setPrice(rs.getInt("price"));
                    item.setName(rs.getString("name"));

                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateItem(Items item){
        String sql = "UPDATE items SET price = ?, name = ? WHERE uuid = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, item.getPrice());
            stmt.setString(2, item.getName());
            stmt.setString(3, item.getUuid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(String uuid){
        String sql = "DELETE FROM items WHERE uuid = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
