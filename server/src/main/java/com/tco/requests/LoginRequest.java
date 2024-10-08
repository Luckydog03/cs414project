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

import java.sql.ResultSet;
import com.tco.database.strategies.LoginStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class LoginRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(LoginRequest.class);
    private transient DatabaseStrategy<ResultSet> databaseStrategy;
    private String username;
    private String hashedPassword;

    private String email;
    private String password;
    private boolean response;
    private int user_id;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.requestType = "login";
    }
    
    // Login User Method
    public int loginUser(String email, String password) {
        try {
            this.databaseStrategy = new LoginStrategy(email, password);
            ResultSet resultSet = this.databaseStrategy.submit();

            if (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String hashedPassword = resultSet.getString("hashed_password");

                if (BCrypt.checkpw(password, hashedPassword)) {
                    log.info("User with email {} and ID {} logged in.", email, user_id);
                    return user_id; // If Authentication suceeds
                }
            }
        } catch (SQLException e) {
            log.error("Error logging in user: {}", e.getMessage());
        }

        return -1; // If Authentication failed
    }

    @Override
    public void buildResponse() {
        log.trace("buildResponse -> {}", 99);
        this.user_id = this.loginUser(this.email, this.password);

        if (this.user_id != -1){
            this.response = true;
        } else {
            this.response = false;
        }

    }
}
