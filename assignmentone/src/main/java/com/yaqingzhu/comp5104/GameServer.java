package com.yaqingzhu.comp5104;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	
	private int whosTurn;
	private ArrayList<PlayerThread> playerPool;

	public ArrayList<PlayerThread> getPlayerPool() {
		if(playerPool == null)
			playerPool = new ArrayList<PlayerThread>();
		return playerPool;
	}

	public void setPlayerPool(ArrayList<PlayerThread> playerPool) {
		this.playerPool = playerPool;
	}

	public int getWhosTurn() {
		return whosTurn;
	}

	public void setWhosTurn(int whosTurn) {
		this.whosTurn = whosTurn;
		
		if(this.whosTurn > 2) {
			this.whosTurn = 0;
		}
	}
	
	public void startGame() {
		PlayerThread currentPlayer;
		
		broadCast("Game start!");
		
		while(true) {
			currentPlayer = getPlayerPool().get(getWhosTurn());
			currentPlayer.getPlayer().setRound(new Round());
			broadCast("Please wait for player " + (getWhosTurn() + 1) + " to finish round.", getWhosTurn());
			currentPlayer.runYourRound();
			
			if(currentPlayer.getPlayer().getScore() >= 6000) {
				broadCast("Winner is " + + (getWhosTurn() + 1) + "! ");
				broadCast("EOG");
				break;
			}
			
			if(currentPlayer.getPlayer().getRound().isWentToSkullIsland()) {
				for(int i = 0; i < 3; i++) {
					if(i != getWhosTurn()) {
						getPlayerPool().get(i).getPlayer().setScore(getPlayerPool().get(i).getPlayer().getScore() - currentPlayer.getPlayer().getRound().getScoreOfSkullIsland());
					}
				}
			}
			setWhosTurn(getWhosTurn() + 1);
		}
	}
	
	private void broadCast(String message) {
		for(int i = 0; i < 2; i++) {
			getPlayerPool().get(i).broadCast(message);
		}
	}
	
	private void broadCast(String message, int skip) {
		for(int i = 0; i < 2; i++) {
			if(i != skip) {
				getPlayerPool().get(i).broadCast(message);
			}
		}
	}

	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(20006);
		GameServer gameServer = new GameServer();
		Socket client = null;  
        int clientNumber = 0;
        while(clientNumber < 3){   
        	clientNumber++;
            client = server.accept();  
            System.out.println("new player");  
            PlayerThread pt = new PlayerThread();
            pt.setSocket(client);
            gameServer.getPlayerPool().add(pt);  
        }  
        gameServer.setWhosTurn(0);
        gameServer.startGame();
        System.out.println("game end"); 
	}
}
