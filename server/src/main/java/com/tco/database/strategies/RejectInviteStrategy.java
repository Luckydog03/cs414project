package com.tco.database.strategies;

import com.tco.database.Database;
import com.tco.game.Invitation;
import com.tco.requests.ConfigRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class RejectInviteStrategy implements DatabaseStrategy<Boolean>{

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    private transient Database database;

    private String invite_id;
    private boolean altered;

    private boolean response;

    public RejectInviteStrategy(String invite_id){
        this.invite_id = invite_id;
        this.altered = false;
    }

    @Override
    public Boolean submit() throws SQLException {

        String sql = "UPDATE invites SET status = 'Rejected' WHERE invite_id = ?";

        try{

            this.database = Database.getInstance();
            Connection conn = database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Set the invite_id parameter in the query
            pstmt.setInt(1, Integer.parseInt(invite_id));
            int rowsAffected = pstmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                log.info("Invite with id " + invite_id + " was successfully rejected.");
                this.altered = true;
            } else {
                log.info("No invite found with id " + invite_id + ", or invite is already rejected.");
            }

        } catch (SQLException e) {
            log.error("SQL error during invite rejection: {}", e.getMessage());
        }

        return this.altered;
    }
}