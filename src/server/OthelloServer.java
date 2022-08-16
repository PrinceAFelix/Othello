package server;

import java.io.*;
import java.net.*;
import java.util.*;

import othello.OthelloNetworkModalViewController;
import othello.OthelloViewController;

/**
 * OthelloServer<br>
 * The OthelloNetworkController represents the server
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-08-15 CST221 Java Application Programming
 * @version 1.0
 * 
 */
public class OthelloServer {
	
	static OthelloNetworkModalViewController server = new OthelloNetworkModalViewController();
	
	static OthelloViewController ovc = new OthelloViewController();
	
	static int port;
	static String host;
	OthelloServer(int p, String h){
		port = p;
		host = h;
	}
	
	public static void main(String[] args){  
		
		
		
	      try
	      {  
	         int i = 1;
	        
	         ServerSocket s = new ServerSocket(port);

	         ovc.displayedMessage.append("\nNegotiating connection to " + host +" on port " + port);
	         
	         while (true)
	         {  
	        	 
	            Socket incoming = s.accept();
	            Runnable r = new OthelloServerThread(incoming);
	            Thread t = new Thread(r);
	            t.start();
	            
	         }
	      }
	      catch (IOException e)
	      {  
	         e.printStackTrace();
	      }
	   }
}



