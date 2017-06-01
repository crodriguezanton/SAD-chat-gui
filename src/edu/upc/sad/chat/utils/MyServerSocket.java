package edu.upc.sad.chat.utils;

import java.net.*;
import java.io.*;


public class MyServerSocket{

	ServerSocket servSock;
	
	public MyServerSocket(int port) throws Exception{
		servSock = new ServerSocket(port);
	}
	
	public MySocket accept(){
		MySocket socket = null;
		try {		
			Socket tmp = servSock.accept();
			socket = new MySocket(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return socket;
	}

}
