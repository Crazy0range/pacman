package com.pacman.pacmannetwork;


import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.pacman.utils.SerializationUtil;
import com.pacman.utils.Settings;



public class PacmanServer  {

	ZContext context;
	Socket publisher;
	
   
	private static PacmanServer instance;
	
	public static void initialize() {
		instance = new PacmanServer();
		
	}
	
	public static void destroy(){
		instance.publisher.close();
		instance.context.destroy();
		instance = null;
	}
	
	private PacmanServer() {
		this.context = new ZContext();
		this.publisher= context.createSocket(ZMQ.XPUB);
		// TODO election value to stored and added here
		//only the tcp address not host, to be added here
		//modded to get dist value
		//this.publisher.connect(Settings.getPublisherURL());
		this.publisher.connect(Settings.getDistPublisherURL());
		
	}
	
	public static void sendData(String subscription,ClientObject tosend){
		System.out.print("------- sending object ");
		//PointVector pntVector = SerializationUtil.serializeData(update);
		System.out.println(subscription + " ---- ");
		instance.publisher.sendMore(subscription);
		instance.publisher.send(SerializationUtil.fromJavaToByteArray(tosend));
		System.out.println("Sending data");
	}


		
	

}
