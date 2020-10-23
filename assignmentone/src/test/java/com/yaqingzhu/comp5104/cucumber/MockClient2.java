package com.yaqingzhu.comp5104.cucumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class MockClient2 implements Runnable{
	Socket client;
	BufferedReader input;
	PrintStream out;
	BufferedReader buf;
	private Thread t;
	String id;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			client = new Socket("127.0.0.1", 20007);
			input = new BufferedReader(new InputStreamReader(System.in));  
	        out = new PrintStream(client.getOutputStream());  
	        buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));       
		} catch (UnknownHostException e1) {
			
		} catch (Exception e1) {
			
		}  
        
    
	}
	
	public String readFromServer() {
		String echo;
		String opt = "";
		try {
			while(buf.ready()) {
				echo = buf.readLine();
				opt = opt + echo;
				System.out.println("Player " + id + " " + echo);
				System.out.println("");
			}
		} catch (IOException e) {

		}
       return opt;
	}
	
	public void writeToServer(String opt) {
		out.println(opt);
	}
	
	public void closeClient() {
		try {
			input.close();
			out.close();
	        client.close();
	        if(client != null){  
	            client.close(); 
	        } 
		} catch (IOException e) {

		}  
         
	}
	
	public void start (String id) {
		this.id = id;
	      System.out.println("Starting Client " + id);
	      if (t == null) {
	         t = new Thread (this, "Client" + id);
	         t.start ();
	      }
	   }

}
