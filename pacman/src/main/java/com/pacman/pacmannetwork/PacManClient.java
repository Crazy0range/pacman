package com.pacman.pacmannetwork;

import org.zeromq.ZMQ;

import com.pacman.model.Pacman;
import com.pacman.utils.SerializationUtil;



public class PacManClient implements Runnable{
	
	private ZMQ.Context context;
	private ZMQ.Socket socket;
	private Pacman _pacmantosend;

	@Override
	public void run() {
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://localhost:5555");
//		String hi = "Hi";
		ClientObject val = new ClientObject("Hi",_pacmantosend);
		byte[] rawByte = SerializationUtil.fromJavaToByteArray(val);
		
		socket.send(rawByte, 0);
		byte[] rawBytes = socket.recv(0);
		ClientObject recVal = (ClientObject) SerializationUtil.fromByteArrayToJava(rawBytes);
		System.out.println(recVal.value);
		
		
		
	}
	
	public void getPacman(Pacman _pacman){
		this._pacmantosend = _pacman;
	}

}
