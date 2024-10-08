package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tco.database.strategies.SendInviteStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class SendInviteRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    // private transient Database database;
    private transient DatabaseStrategy<Boolean> databaseStrategy;

    private String sender_user_id;
    private String receiver_email;
    private boolean response;

    public SendInviteRequest(String sender_user_id, String receiver_email){
        this.sender_user_id = sender_user_id;
        this.receiver_email = receiver_email;
        this.requestType = "sendinvite";
    }

    public boolean sendInvitation(){

        try{

            boolean res;
            this.databaseStrategy = new SendInviteStrategy(sender_user_id, receiver_email);
            res = databaseStrategy.submit();
            return res;
            
        }catch(SQLException e){
            log.error("Error creating the invite in database: {}", e.getMessage());
            return false;
        }

        // Final killswitch if error prior to database access
        // return false;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", this);
        this.response = this.sendInvitation();
    }
}
