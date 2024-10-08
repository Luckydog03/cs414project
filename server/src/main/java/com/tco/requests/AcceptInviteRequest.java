package com.tco.requests;

import com.tco.database.Database;
import com.tco.game.Invitation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tco.database.strategies.AcceptInviteStrategy;
import com.tco.database.strategies.DatabaseStrategy;

public class AcceptInviteRequest extends Request{
    
    private static final transient Logger log = LoggerFactory.getLogger(AcceptInviteRequest.class);
    private transient Database database;
    
    private String invite_id;
    
    private String player1_id;
    private String player2_id;
    private String match_id;

    private boolean response;

    public AcceptInviteRequest(String invite_id){
        this.invite_id = invite_id;
    }
    
    /* here is the logic needed for acceptInvite methods for the database itself
    
    In order to tie match_id from the invites table to the matches table:

    Regarding the match_id, it would be more logical to create a match first and then reference its ID in the invites table. This way, we avoid having orphan rows in our matches table that are not connected to any invite. Here's how the process would look:

    When an invite is accepted, this will create a new row in the matches table.
    Then it will capture the auto-generated match_id from this new row.
    Afterwards, update the corresponding invite row with this match_id.
    */
    public AcceptInviteResponse acceptInvite(){

        // first call to database to retrieve both user ids for match creation
        String sqlFindInvite = "SELECT sender_id, receiver_id, status FROM invites WHERE invite_id = ?";

        try{
            this.database = Database.getInstance();
            Connection conn = database.getConnection();

            PreparedStatement statement1 = conn.prepareStatement(sqlFindInvite);

            statement1.setString(1, this.invite_id);
            try(ResultSet result = statement1.executeQuery();){

                if(result.next()){

                    // for testing purposes
                    String status = result.getString("status");

                    // Check if the invite has already been accepted; mtch duplication bug fixed
                    if ("Accepted".equals(status)) {
                        log.info("Invite has already been accepted");
                        return new AcceptInviteResponse(true, null);
                    }

                    String player1_id = result.getString("sender_id");
                    String player2_id = result.getString("receiver_id");
                    boolean matchStarted = createMatch(conn, player1_id, player2_id);

                    if (matchStarted){
                        // log.info("Should return true! Along with htis match ID: " + this.match_id);
                        return new AcceptInviteResponse(true, this.match_id);
                    }
                }
            }
        }
        catch(SQLException e){
            log.error("Error accepting the invite in database: {}", e.getMessage());
            return new AcceptInviteResponse(false, null);
        }

        return new AcceptInviteResponse(false, null);
    }

    public boolean createMatch(Connection conn, String user1_id, String user2_id){

        String sqlCreateMatchRecord = "INSERT INTO matches (match_id, white_id, black_id, fen_state) VALUES (?,?,?,?)";
        String sqlUpdateInviteMatch = "UPDATE invites SET match_id = ?, status = 'Accepted' WHERE invite_id = ?";

        try{

            // needed to ensure all tables update together, and correctly. Either both or none are updated
            // for invites and matches tables
            conn.setAutoCommit(false);

            // Random assignment done here before execution of match table insert; I am very proud
            Random random = new Random();
            boolean assignPlayerOneAsWhite = random.nextBoolean();
            String whitePlayerId = assignPlayerOneAsWhite ? user1_id : user2_id;
            String blackPlayerId = assignPlayerOneAsWhite ? user2_id : user1_id;

            // FEN string initiation here to test better addition to database record
            String initial_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";


            // continuation of the statement prep and execution when it is finished with random assignment here
            try(PreparedStatement statement2 = conn.prepareStatement(sqlCreateMatchRecord);){

                // generate a new UUID for the match id in invites AND matches...
                // because i guess generateKeys() does not with out database... this took 3 hours
                String newMatchId = UUID.randomUUID().toString();
                statement2.setString(1, newMatchId);

                statement2.setString(2, whitePlayerId);
                statement2.setString(3, blackPlayerId);

                // // test users and fen insert failures
                // log.info("White player being inserted: " + whitePlayerId);
                // log.info("Black player being inserted: " + blackPlayerId);
                // log.info("FEN State being inserted: " + initial_fen);

                statement2.setString(4, initial_fen);
                int testQuery = statement2.executeUpdate();


                // //test
                // log.info("statement test, here is the match id: " + newMatchId);

                // Update the invites table with the new match_id returned
                try(PreparedStatement statement3 = conn.prepareStatement(sqlUpdateInviteMatch);){

                    // this is for returning the match_id created to the client for game initaiation
                    this.match_id = newMatchId;
                    // // test
                    // log.info("And here is the invite ID for check: " + this.invite_id);

                    statement3.setString(1, newMatchId);
                    statement3.setString(2, this.invite_id);
                    int rowsAffected = statement3.executeUpdate();

                    if (rowsAffected > 0) {

                        // if this succeeds, then commit transaction to both tables
                        conn.commit();
                        // log.info("Looks like it passed, here is the match ID: " + newMatchId);
                        return true;
                    }
                }


            }

        } catch (SQLException e) {
            log.error("Error creating match in database: {}", e.getMessage());


            // nested try-catch block inside existing catch block to handle potential exceptions from the rollback() call
            try {
                if (conn != null) {
                    // callback function in case of invites or matches table manip failures
                    conn.rollback();
                }
            } catch (SQLException ex) {
                log.error("Error rolling back transaction: {}", ex.getMessage());
            }
            return false;
        }

        // kill switch if failed any and all transactions
        return false;

    }

    // Using the response class to pass both success variable and the created match ID after full completion of request
    public class AcceptInviteResponse {
        private boolean success;
        private String match;

        public AcceptInviteResponse(boolean s, String m){
            this.success = s;
            this.match = m;
        }

        public boolean getSuccess(){
            return this.success;
        }

        public String getMatchID(){
            return this.match;
        }
    }

    @Override
    public void buildResponse(){
        log.trace("buildResponse -> {}", this);
        AcceptInviteResponse response = this.acceptInvite();
        this.response = response.getSuccess();
        this.match_id = response.getMatchID();
    }
}
