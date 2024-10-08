package com.tco.requests;

import com.tco.database.Database;
import com.tco.game.Invitation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tco.database.strategies.RejectInviteStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class RejectInviteRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(RejectInviteRequest.class);
    // private transient Database database;
    private transient DatabaseStrategy<Boolean> databaseStrategy;

    private String invite_id;
    private boolean altered;
    private boolean response;

    public RejectInviteRequest(String invite_id){
        this.invite_id = invite_id;
        this.altered = false;
        this.requestType = "rejectinvite";
    }

    public boolean rejectInvite() {

        try{

            this.databaseStrategy = new RejectInviteStrategy(invite_id);
            this.altered = databaseStrategy.submit();

        } catch (SQLException e) {
            log.error("SQL error during invite rejection: {}", e.getMessage());
        }

        return this.altered;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", this);
        this.response = this.rejectInvite();
    }
}
