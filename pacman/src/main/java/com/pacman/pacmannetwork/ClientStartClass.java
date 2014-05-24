package com.pacman.pacmannetwork;


public class ClientStartClass {

	public static void main(String[] args) throws InterruptedException{
		String leaderUID="";
		
		PacManClient.initializePacmanClient(leaderUID, new PacManClient.Callback() {
			
			public void onMessage(byte[] data) {
				// TODO Auto-generated method stub
				
			}
		});
		}

}
