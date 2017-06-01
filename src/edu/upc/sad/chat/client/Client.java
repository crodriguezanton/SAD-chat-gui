package edu.upc.sad.chat.client;

import edu.upc.sad.chat.gui.ClientGUI;
import edu.upc.sad.chat.utils.MySocket;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
	
	private static MySocket socket;
	private static String nick;
	private static ClientGUI gui;

	public Client(String host, int port, String nickname, ClientGUI clientgui){
		try {
			socket = new MySocket(host, port);
			nick = nickname;
			gui = clientgui;

			socket.send(nick);
			new Thread(new Receiver(socket)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendText(String text) {
		socket.send(text);
	}

	public static void closeSocket() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class Receiver implements Runnable{
		MySocket sock;

		public Receiver(MySocket sock) {
			this.sock = sock;		
		}
		public void run(){
			try{	
				String line;
				while((line = sock.receive())!= null) {
					String[] tmp = line.split(":");
					switch (tmp[0]){
						case "UP":
							gui.receiveText(tmp[1]+" has joined the chat");
							gui.addUser(tmp[1]);
							break;
						case "WAS":
							gui.addUser(tmp[1]);
							break;
						case "DOWN":
							gui.receiveText(tmp[1]+" has left the chat");
							gui.removeUser(tmp[1]);
							break;
						default:
							gui.receiveText(line);
							break;
					}

				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
