package com.pacman.pacmanserver;


import java.util.Hashtable;
import java.util.Set;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.pacman.utils.SerializationUtil;



public class PacmanServer implements Runnable {

	Set<String> hosts;
	public void run(){
	Context context = ZMQ.context(1);
	// Socket to talk to clients
	Socket socket1 = context.socket(ZMQ.REP);
	socket1.bind("tcp://*:5555");
	while(true){
		byte[] incoming = socket1.recv(0);
		ClientObject rep = (ClientObject) SerializationUtil.fromByteArrayToJava(incoming);
		System.out.println(rep.value);
		ClientObject reply = new ClientObject("Recieved msg");
		socket1.send( SerializationUtil.fromJavaToByteArray(reply),1);
	}
	
	}
}
