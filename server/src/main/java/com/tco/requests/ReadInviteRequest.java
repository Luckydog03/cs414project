package com.tco.requests;

import com.tco.database.Database;
import com.tco.game.Invitation;
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

import com.tco.database.strategies.ReadInviteStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class ReadInviteRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ReadInviteRequest.class);
    private transient DatabaseStrategy<List<Invitation>> databaseStrategy;
    
    private String receiver_id;
    private List<Invitation> invite_list;

    public ReadInviteRequest(String receiver_id) {
        this.receiver_id = receiver_id;
        this.requestType = "readinvite";
    }

    public List<Invitation> readinvites(String receiver_id){

        List<Invitation> invites = new ArrayList<>();

        try{
            this.databaseStrategy = new ReadInviteStrategy(receiver_id);
            invites = databaseStrategy.submit();

        }catch(SQLException e){
            log.error("Error reading/handling invites from the database in request {}", e.getMessage());
            throw new RuntimeException("ERROR:", e);
        }

        return invites;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.invite_list = this.readinvites(this.receiver_id);
    }
}

