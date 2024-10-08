package com.tco.database.strategies;

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
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public class AcceptInviteStrategy implements DatabaseStrategy<Boolean>{
    // many methods, may not get to it

    @Override
    public Boolean submit() throws SQLException{
        return false;
    }
}