package com.tco.requests;

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

import com.tco.database.strategies.ReadUserStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class ReadUserRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ReadInviteRequest.class);
    private transient DatabaseStrategy<List<User>> databaseStrategy;
    
    private String receiver_id;
    private List<User> user_list;

    public ReadUserRequest(String receiver_id) {
        this.receiver_id = receiver_id;
        this.requestType = "readuser";
    }

    public List<User> readusers(String receiver_id){
        List<User> users = new ArrayList<>();
        try{
            this.databaseStrategy = new ReadUserStrategy(receiver_id);
            users = databaseStrategy.submit();

        }catch(SQLException e){
            log.error("Error reading/handling matches from the database in request {}", e.getMessage());
            throw new RuntimeException("ERROR:", e);
        }

        return users;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}",this);
        this.user_list = this.readusers(this.receiver_id);
    }
}

