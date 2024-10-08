package com.tco.requests;

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
import com.tco.database.strategies.RegisterStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class RegisterRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);
    private transient DatabaseStrategy<Integer> databaseStrategy;
    //private String username;
    //private String hashedPassword;

    private String email;
    private String username;
    private String password;

    private boolean response;
   

    public RegisterRequest(String email, String password ) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.requestType = "register";
    }
    
    
     // Add User Method
     public Boolean addUser(String userEmail,  String password) {
        try {
            this.databaseStrategy = new RegisterStrategy(email, password);
            int checkStatement = databaseStrategy.submit();

            if (checkStatement > 0) {
                return true; 
            } 

        } catch (SQLException e) {
            log.error("Error adding user to the database: {}", e.getMessage());
            // Handle the error appropriately, e.g., throw a custom exception or return false
            return false;
        }
        return false;
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.response = this.addUser(this.email, this.password);
    }
}
