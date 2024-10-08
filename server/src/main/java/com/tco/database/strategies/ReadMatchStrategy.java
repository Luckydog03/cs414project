package com.tco.database.strategies;

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

public class ReadMatchStrategy implements DatabaseStrategy<List<Match>> {
    private String receiver_id;

    public ReadMatchStrategy(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Override
    public List<Match> submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String selectSql = "SELECT * FROM matches WHERE white_id = ? OR black_id = ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1, receiver_id);
        statement.setString(2, receiver_id);
        List<Match> matches = new ArrayList<>();
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                String match_id = result.getString("match_id");
                String white_id = result.getString("white_id");
                String black_id = result.getString("black_id");
                String fen_state = result.getString("fen_state");
                String player_turn = result.getString("player_turn");
                String status = result.getString("status");
                String winner_id = result.getString("winner_id");
                Match match = new Match(match_id, white_id, black_id, fen_state, player_turn, status, winner_id);
                matches.add(match);
            }
        }
        return matches;
    }
}
