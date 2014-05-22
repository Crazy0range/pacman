package com.pacman.pacmanserver;

import org.zeromq.ZMQ;

import com.pacman.utils.SerializationUtil;







public class PacManClient implements Runnable{
	
	ZMQ.Context context;
	ZMQ.Socket socket;

	@Override
	public void run() {
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://localhost:5555");
		String hi = "Hi";
		ClientObject val = new ClientObject("Hi");
		byte[] rawByte = SerializationUtil.fromJavaToByteArray(val);
		
		socket.send(rawByte, 0);
		byte[] rawBytes = socket.recv(0);
		ClientObject recVal = (ClientObject) SerializationUtil.fromByteArrayToJava(rawBytes);
		System.out.println(recVal.value);
		
		
		
	}

}
