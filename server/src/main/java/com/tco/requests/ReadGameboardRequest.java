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

import com.tco.database.strategies.ReadGameboardStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class ReadGameboardRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ReadInviteRequest.class);
    private transient DatabaseStrategy<Match> databaseStrategy;
    
    private String match_id;
    private Match game_board;

    public ReadGameboardRequest(String match_id) {
        this.match_id = match_id;
        this.requestType = "readgameboard";

    }

    public Match readgameboard(String match_id){

        Match game_board;
        try{
            this.databaseStrategy = new ReadGameboardStrategy(match_id);
            game_board = databaseStrategy.submit();
        }catch(SQLException e){
            log.error("Error reading/handling matches from the database in request {}", e.getMessage());
            throw new RuntimeException("ERROR:", e);
        }

        return game_board;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.game_board = this.readgameboard(this.match_id);
    }
}

