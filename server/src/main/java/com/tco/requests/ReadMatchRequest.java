package com.tco.requests;

import com.tco.database.Database;
import com.tco.game.Match;
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

import com.tco.database.strategies.ReadMatchStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class ReadMatchRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ReadInviteRequest.class);
    private transient DatabaseStrategy<List<Match>> databaseStrategy;
    
    private String receiver_id;
    private List<Match> match_list;

    public ReadMatchRequest(String receiver_id) {
        this.receiver_id = receiver_id;
        this.requestType = "readmatch";

    }

    public List<Match> readmatches(String receiver_id){
        List<Match> matches = new ArrayList<>();
        try{
            this.databaseStrategy = new ReadMatchStrategy(receiver_id);
            matches = databaseStrategy.submit();
        }catch(SQLException e){
            log.error("Error reading/handling matches from the database in request {}", e.getMessage());
            throw new RuntimeException("ERROR:", e);
        }

        return matches;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.match_list = this.readmatches(this.receiver_id);
    }
}

