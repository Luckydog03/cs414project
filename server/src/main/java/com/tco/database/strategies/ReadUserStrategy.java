package com.tco.database.strategies;

import com.tco.database.Database;
import com.tco.player.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.java_websocket.WebSocket;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadUserStrategy implements DatabaseStrategy<List<User>> {
    private String receiver_id;

    public ReadUserStrategy(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Override
    public List<User> submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String selectSql = "SELECT user_id, email FROM user WHERE user_id != ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1, receiver_id);
        List<User> users = new ArrayList<>();
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                String user_id = result.getString("user_id");
                String email = result.getString("email");
                User user = new User(user_id, email);
                users.add(user);
            }
        }
        return users;
    }
}
