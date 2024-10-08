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

public class RegisterStrategy implements DatabaseStrategy<Integer> {
    private String email;
    private String password;

    public RegisterStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Integer submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String insertSql = "INSERT INTO user (email, hashed_password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setString(1, email);
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        statement.setString(2, hashedPassword);
        return statement.executeUpdate();
    }
}