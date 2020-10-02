package com.yaqingzhu.comp5104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GameClient {

	public static void main(String[] args) throws IOException {  
        Socket client = new Socket("127.0.0.1", 20006);  
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
        PrintStream out = new PrintStream(client.getOutputStream());  
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));  
        boolean flag = true;  
        while(flag){  
                try{    
                    String echo = buf.readLine();
                    System.out.println(echo);
                    if(echo.contains("your input")) {
                    	
                        String str = input.readLine();   
                        out.println(str);        
                    } 
                    
                    if(echo.equals("EOG")) {
                    	flag = false;
                    }
                }catch(SocketTimeoutException e){  
                    System.out.println("Time out, No response");  
                }  
        }  
        input.close();  
        if(client != null){  
            client.close(); 
        }  
    }  
}
