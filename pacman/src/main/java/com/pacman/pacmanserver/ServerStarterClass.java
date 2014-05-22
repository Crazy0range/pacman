package com.pacman.pacmanserver;

public class ServerStarterClass {

	public static void main(String[] args) {
	 Thread server = new Thread(new PacmanServer());
	 
	 server.start();

	}

}
