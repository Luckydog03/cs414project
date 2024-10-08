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

public class UpdateGameboardStrategy implements DatabaseStrategy<Boolean> {
    private String match_id;
    private String fen_state;
    private String user_id;

    public UpdateGameboardStrategy(String match_id, String fen_state, String user_id) {
        this.match_id = match_id;
        this.fen_state = fen_state;
        this.user_id = user_id;
    }

    @Override
    public Boolean submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String selectSql = "SELECT white_id, black_id, player_turn FROM matches WHERE (match_id = ?)";
        String updateSql = "UPDATE matches SET fen_state = ?, player_turn = ? WHERE (match_id = ?)";
        PreparedStatement selectStatement = connection.prepareStatement(selectSql);
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
        selectStatement.setString(1, match_id);
        try (ResultSet result = selectStatement.executeQuery()) {
            result.next();
            String white_id = result.getString("white_id");
            String black_id = result.getString("black_id");
            String player_turn = result.getString("player_turn");

            if (user_id.equals(white_id) && player_turn.equals("white")) {
                updateStatement.setString(1, fen_state);
                updateStatement.setString(2, "black");
                updateStatement.setString(3, match_id);
                updateStatement.executeUpdate();
                return true;
            } else if (user_id.equals(black_id) && player_turn.equals("black")) {
                updateStatement.setString(1, fen_state);
                updateStatement.setString(2, "white");
                updateStatement.setString(3, match_id);
                updateStatement.executeUpdate();
                return true;
            }
        }
        return false;
    }
}