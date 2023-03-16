package com.example.demotest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
	
private static final int PORT_NUMBER = 4432;
	
	public static void main(String[] args) throws IOException{
		
		System.out.println(":::                                                :::");
		System.out.println(":::       Socket Application  Process Start        :::");
		System.out.println(":::                                                :::");
		
		try(ServerSocket server = new ServerSocket(PORT_NUMBER)){
			while(true){
				Socket connection = server.accept();
				Thread task = new SocketThreadServer(connection);
				task.start();
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
}

