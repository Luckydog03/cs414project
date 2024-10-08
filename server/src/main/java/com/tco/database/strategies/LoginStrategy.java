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

public class LoginStrategy implements DatabaseStrategy<ResultSet> {
    private String email;
    private String password;

    public LoginStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public ResultSet submit() throws SQLException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String selectSql = "SELECT user_id, hashed_password FROM user WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1, email);
        return statement.executeQuery();
    }
}