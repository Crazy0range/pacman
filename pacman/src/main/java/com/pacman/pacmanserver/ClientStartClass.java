package com.pacman.pacmanserver;

public class ClientStartClass {

	public static void main(String[] args) {
		Thread client = new Thread(new PacManClient());
		client.start();
	}

}
