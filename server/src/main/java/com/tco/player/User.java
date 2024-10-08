package com.tco.player;

//import com.tco.player.History;
import java.util.*;
import com.tco.game.Invitation;

//considering using some easy relational frameworls for working with users/players
import javax.persistence.Entity;
import javax.persistence.Table;

// @Entity
// @Table(name="user")
public class User {

	private String user_id;
	//private String fullname;
	//private String displayname;
	private String email;
	//private boolean loggedIn;
	//private String password;
	//public History history;
	
	public User(String user_id, String email){
		this.user_id = user_id;
		//this.fullname = fullname;
		//this.displayname = displayname;
		this.email = email;
		//this.loggedIn = false;
		//this.password = hpword;
		//this.history.clean();
	}

	public void historyTable(String userID){
		//CALL SQL TABLE FOR USER HISTORY
		//returns the table in form of wins/losses/active
		//active games will link to match states with turns revolving
	}
	/* 
	public void changeFullName(String newName){
		this.fullname = newName;
	}

	public void changeDisplayName(String newName){
		this.displayname = newName;
	}

	public String showFullName(){
		return this.fullname;
	}

	public String showDisplayName(){
		return this.displayname;
	}

	public boolean isLoggedIn(){
		return this.loggedIn;
	}

	public void loguserIn(){
		this.loggedIn = true;
	}*/
}
