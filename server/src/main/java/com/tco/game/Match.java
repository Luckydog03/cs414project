package com.tco.game;

import java.util.*;
import java.io.*;

public class Match {
	private String white_id;
    private String black_id;
    private String match_id;
    private String fen_state;
    private String player_turn;
    private String status; 
    private String winner_id;


    public Match(String match_id, String white_id, String black_id, String fen_state, String player_turn, String status, String winner_id){
        this.match_id = match_id;
        this.white_id = white_id;
        this.black_id = black_id;
        this.fen_state = fen_state;
        this.player_turn = player_turn;
        this.status = status;
        this.winner_id = winner_id;
    }
    public String getFenString(){
        return this.fen_state;
    }
}
