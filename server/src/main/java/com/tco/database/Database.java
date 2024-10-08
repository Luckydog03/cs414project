package com.tco.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private Connection conn = null;
    private PreparedStatement nameStatement = null;
    private PreparedStatement countStatement = null;
    private Statement test = null;
    private static Database instance = null;

    public Database() {
        try {
            conn = DriverManager.getConnection(Credential.url(), Credential.USER, Credential.PASSWORD);
            test = conn.createStatement();
        } catch (SQLException se) {
            log.error("SQL Exception: " + se.getMessage());
        }
    }

    private static Logger log = LoggerFactory.getLogger(Database.class);

    // Custom method to execute SQL query
    // Overridden executeQuery cited by GPT-3.5 on 10/17/2023
    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException se) {
            log.error("SQL Exception: " + se.getMessage());
            throw se;
        }
        return result;
    }
    

    public Connection getConnection() {
        return conn;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public int testDB(){
        //Integer result = 10;
        //try {
            //ResultSet results = test.executeQuery("SELECT * FROM test");
            //while(results.next()){
                //result = new Integer(results.getInt("name"));
            //}
            /* results.first();
            result = new Integer(results.getInt("name")); */
       // } catch (SQLException se){
            //log.error("SQL Exceptionn: " + se.getMessage());
        //}
       // return this;
        return 10;
    }

}


