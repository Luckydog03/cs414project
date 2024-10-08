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

public class ReadInviteStrategy implements DatabaseStrategy<List<Invitation>>{

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private String receiver_id;

    public ReadInviteStrategy(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Override
    public List<Invitation> submit() throws SQLException{

        List<Invitation> invites = new ArrayList<>();
        String sql = "SELECT i.invite_id, i.sender_id, u.email FROM invites i " +
            "JOIN user u ON i.sender_id = u.user_id WHERE i.receiver_id = ? AND i.status = 'Pending'";

        Database database = new Database();
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, receiver_id);
        try(ResultSet result = statement.executeQuery();){

            while (result.next()){
                String sender_id = result.getString("sender_id");
                String invite_id = result.getString("invite_id");
                String sender_email = result.getString("email");
                Invitation invitation = new Invitation(sender_id, receiver_id, invite_id, sender_email);
                invites.add(invitation);
            }
        }

        return invites;
    }
}