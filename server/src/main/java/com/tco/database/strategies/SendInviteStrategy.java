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

public class SendInviteStrategy implements DatabaseStrategy<Boolean>{

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private String sender_user_id;
    private String receiver_email;
    private boolean response;

    public SendInviteStrategy(String sender_user_id, String receiver_email){
        this.sender_user_id = sender_user_id;
        this.receiver_email = receiver_email;
    }

    @Override
    public Boolean submit() throws SQLException {

        String sqlForReceiverID = "SELECT user_id FROM user WHERE email = ?";
        String sqlForInvite = "INSERT INTO invites (sender_id, receiver_id, status) VALUES (?,?,?)";

        // bug fix queries to halt duplicates
        String sqlCheckExistingInvite = "SELECT invite_id FROM invites WHERE sender_id = ? AND receiver_id = ? AND status = 'Pending'";
        String sqlUpdateExistingInvite = "UPDATE invites SET status = 'Rejected' WHERE invite_id = ?";


        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        PreparedStatement ReceiverIDStatement = conn.prepareStatement(sqlForReceiverID);
        ReceiverIDStatement.setString(1, receiver_email);
        ResultSet receiverIDResult = ReceiverIDStatement.executeQuery();

        if(receiverIDResult.next()){

            String receiver_user_id = receiverIDResult.getString("user_id");

            // Check for existing invites
            try (PreparedStatement checkInviteStatement = conn.prepareStatement(sqlCheckExistingInvite)) {
                checkInviteStatement.setString(1, sender_user_id);
                checkInviteStatement.setString(2, receiver_user_id);
                try (ResultSet existingInviteResult = checkInviteStatement.executeQuery()) {
                    if (existingInviteResult.next()) {
                        // Existing invite found, update it to 'Rejected'
                        try (PreparedStatement updateStatement = conn.prepareStatement(sqlUpdateExistingInvite)) {
                            updateStatement.setString(1, existingInviteResult.getString("invite_id"));
                            updateStatement.executeUpdate();
                            log.info("Replacing old invitation with new one.");
                        }
                    }
                }
            }


            PreparedStatement statement = conn.prepareStatement(sqlForInvite, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sender_user_id);
            statement.setString(2, receiver_user_id);
            statement.setString(3, "Pending");

            int check = statement.executeUpdate();

            if (check > 0){
                return true;
            }
           
        }
        
        return false;
    }

}
