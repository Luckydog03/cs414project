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

import com.tco.database.strategies.UpdateGameboardStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class UpdateGameboardRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ReadInviteRequest.class);
    private transient DatabaseStrategy<Boolean> databaseStrategy;
    
    private String user_id;
    private String match_id;
    private String fen_state;
    private Match game_board;
    private boolean update_successful;

    public UpdateGameboardRequest(String match_id, String fen_state, String user_id) {
        this.match_id = match_id;
        this.fen_state = fen_state;
        this.user_id = user_id;
        this.requestType = "updategameboard";
    }

    public boolean updategameboard(String match_id, String fen_state, String user_id){
        try{
           this.databaseStrategy = new UpdateGameboardStrategy(match_id, fen_state, user_id);
           return databaseStrategy.submit();

        }catch(SQLException e){
            log.error("Error reading/handling matches from the database in request {}", e.getMessage());
            throw new RuntimeException("ERROR:", e);
        }
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.update_successful = this.updategameboard(this.match_id, this.fen_state, this.user_id);
    }
}

