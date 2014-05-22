package com.pacmanserver;

import java.util.List;

import com.pacman.model.Pacman;
import org.jeromq.ZContext;
import org.jeromq.ZMQ;
import org.jeromq.ZMQ.Socket;



public class PacmanServer {

	
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
//		this.publisher.connect(Settings.getPublisherURL());
		this.publisher.connect("127.0.0.1");
		
	}
	
	public static void sendList(String subscription,Pacman _pacman){
		System.out.println("------- sending vector list");
//		Pacman pntVector = SerializationUtil.serializeData(_pacman);
		System.out.println(subscription + " ---- ");
		instance.publisher.sendMore(subscription);
//		instance.publisher.send(_pacman);
	}
//	
//	
//	public static void sendAdminObj(ReqObject reqObj){
//		instance.publisher.sendMore(Variables.ADMIN_TOPIC);
//		instance.publisher.send(reqObj.toByteArray());
//		
//	}
}
