package com.pacman.ring.server;


import java.util.ArrayList;
import java.util.UUID;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.pacman.ring.model.Acknowledge;
import com.pacman.ring.model.Identity;
import com.pacman.ring.model.Response;
import com.pacman.utils.SerializationUtil;
import com.pacman.utils.Variables;

public class RegistryServer implements Runnable {

	public RegistryServer(int totalNodeCountAllowed) {
		super();

		this.totalNodesAllowed = totalNodeCountAllowed;
		registry = new ArrayList <Identity>();
	}

	int totalNodesAllowed;
	ArrayList<Identity> registry;

	public void run() {


		this.createRegistry();
		this.sendToPeers();
		System.out.println("Created the registry and send in the nodes");

		// this.socket.close();
		// context.term();

	}

	private synchronized void createRegistry() {
		int count = 0;
		int tokenValue = 1;
		Context context = ZMQ.context(1);
		
		// Socket to talk to clients
		Socket server = context.socket(ZMQ.REP);
		server.bind("tcp://*:5555");
		System.out.println("Started Server");
		while (!Thread.currentThread().isInterrupted()) {
			byte[] reply = server.recv(0);
			System.out.println("Recieved a response");
			Response data = (Response) SerializationUtil
					.fromByteArrayToJava(reply);
			Acknowledge ack = new Acknowledge(Variables.ACK,tokenValue);
		
			if (count < totalNodesAllowed) {
				if (data.getRequestType().equalsIgnoreCase(Variables.ALIVE)) {
					server.send(SerializationUtil.fromJavaToByteArray(ack),1);
					String tcpPoint = data.getTcpEndpoint();
					UUID uid = data.getId();
					// todo add id to this data.getID
					Identity id = new Identity(tcpPoint, uid);
					System.out.println("Added node:"+tcpPoint );
					this.registry.add(id);
					count++;
					tokenValue++;
					if(count==totalNodesAllowed)
						break;
				}

			} else {
				Acknowledge ack1 = new Acknowledge(Variables.LIMIT,0);
				server.send(SerializationUtil.fromJavaToByteArray(ack),1);
				break;
			}
		}
		System.out.println("Exited createRegistry");
		server.close();
		context.term();
	}

	
	public void sendToPeers(){
		//sending the tokens across when you get the value
	    Response resp;
		Socket temSock;
		Context context1;
		if(this.registry.size()==totalNodesAllowed){
			//registry.keys()
			Identity currentIdentity=null;
			Identity rightIdentity = null;
			for (int i=0; i< totalNodesAllowed; i++) {
				System.out.println("In create peers");
			if (i == registry.size() - 1) {
				currentIdentity = this.registry.get(i);
				rightIdentity = this.registry.get(0);
				} else {
					currentIdentity = this.registry.get(i);
					rightIdentity = this.registry.get(i+1);
				}
			context1 = ZMQ.context(1);
			temSock = context1.socket(ZMQ.REQ);	
			temSock.connect( currentIdentity.getAddress()+":"+Variables.NodePort);
			System.out.println(currentIdentity.getAddress());
			resp = new Response(rightIdentity.getTokenID(),rightIdentity.getAddress(),Variables.NNValue);
			System.out.println(SerializationUtil.fromJavaToByteArray(resp));
			temSock.send(SerializationUtil.fromJavaToByteArray(resp));
			System.out.println("Send dta");
			byte[] ack = temSock.recv();
			Object command = SerializationUtil.fromByteArrayToJava(ack);
		     if(command instanceof Acknowledge){
		    	 Acknowledge msg = (Acknowledge) command;
		    	 System.out.println(msg.getResponse());
		     }
		     System.out.println("Not Ack");
		    temSock.close();
			context1.term();
		}
		
		}
		
	}
	

}
