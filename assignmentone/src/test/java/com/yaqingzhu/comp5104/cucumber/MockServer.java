package com.yaqingzhu.comp5104.cucumber;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.yaqingzhu.comp5104.*;

public class MockServer implements Runnable {

	public GameServer gs;
	ServerSocket server;
	Socket client;
	private Thread t;
	
	public MockServer() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			server = new ServerSocket(20006);
			System.out.println("Server is running");  
			gs = new GameServer();
			client = null;  
	        int clientNumber = 0;
	        while(clientNumber < 3){   
	        	clientNumber++;
	            client = server.accept();  
	            System.out.println("new player");  
	            PlayerThread pt = new PlayerThread();
	            pt.setSocket(client);
	            pt.setFakeRamdon(true);
	            pt.getPlayer().getRound().isFakeRandom = true;
	            gs.getPlayerPool().add(pt);  
	        }  
	        gs.setWhosTurn(0);
	        gs.startGame();
	        System.out.println("game end"); 
	        server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void start () {
	      System.out.println("Starting Server");
	      if (t == null) {
	         t = new Thread (this, "server");
	         t.start ();
	      }
	   }

}
