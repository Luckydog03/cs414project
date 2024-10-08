package com.tco.database;

public class Credential {
    
    // set in .bashrc or equivalent
    final static String USER = System.getenv("DB_USERNAME");
    final static String PASSWORD = System.getenv("DB_PASSWORD");
    
    // connection information when using port forwarding from localhost - ## is your team number
    final static String URL_DEFAULT = "jdbc:mariadb://faure.cs.colostate.edu/cs414_team57";
    final static String URL_OFF_CAMPUS = "jdbc:mariadb://127.0.0.1:56247/cs414_team57";
    final static String URL_DOCKER = "jdbc:mariadb://127.0.0.1:3306/cs414_team57";

    static String url() {
        String useTunnel = System.getenv("CS414_USE_DATABASE_TUNNEL");
        String onDocker = System.getenv("CS414_DOCKER");
        if(useTunnel != null && useTunnel.equals("true")) {
            return URL_OFF_CAMPUS;
        }
        else if(onDocker != null && onDocker.equals("true")){
            return URL_DOCKER;
        }
        else {
            return URL_DEFAULT;
        }
    }
}