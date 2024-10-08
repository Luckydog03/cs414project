package com.tco.player;

import java.util.*;

public class History {
	
	//private User user;
	private int winCount;
	private int lossCount;
	//private List<Match> activeGames;
	private ArrayList<Integer> tableData; 

	History(){
		this.tableData = new ArrayList<Integer>();
	}

	public void clean(){
		for(int i : tableData){
			tableData.set(i, 0);
		}
	}

	public ArrayList returnList(){
		return this.tableData;
	}

	public void addtoList(Integer game){
		this.tableData.add(game);
	}

	public void removefromList(Integer game){
		this.tableData.remove(game);
	}


}
