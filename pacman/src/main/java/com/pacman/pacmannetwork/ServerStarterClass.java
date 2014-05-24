package com.pacman.pacmannetwork;


public class ServerStarterClass {

	public static void startMainServer() throws InterruptedException{
		
		Thread relay = new Thread(new Forwarder());
		relay.setDaemon(true);
		PacmanServer.initialize();
		relay.start();
		System.out.println("Server has started");
        relay.join();
		}
}


