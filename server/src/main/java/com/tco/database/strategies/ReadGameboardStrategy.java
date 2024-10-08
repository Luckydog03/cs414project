package com.tco.database.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.mindrot.jbcrypt.BCrypt;

import com.tco.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.ResultSet;
import com.tco.game.Match;

public class ReadGameboardStrategy implements DatabaseStrategy<Match> {
    private String match_id;

    public ReadGameboardStrategy(String match_id) {
        this.match_id = match_id;
    }

    @Override
    public Match submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String selectSql = "SELECT * FROM matches WHERE match_id = ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1, match_id);
        try (ResultSet result = statement.executeQuery()) {
            result.next();
            String white_id = result.getString("white_id");
            String black_id = result.getString("black_id");
            String fen_state = result.getString("fen_state");
            String player_turn = result.getString("player_turn");
            String status = result.getString("status");
            String winner_id = result.getString("winner_id");
            return new Match(match_id, white_id, black_id, fen_state, player_turn, status, winner_id);
        }
    }
}