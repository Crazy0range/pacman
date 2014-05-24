package com.pacman.pacmannetwork;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.pacman.utils.SerializationUtil;
import com.pacman.utils.Settings;



public class PacManClient implements Runnable{
	
	private static PacManClient nodeInstance;
	private static Thread nodeThread;
	private boolean execute;
    ZContext context;
	Socket subscriber;
	String electedLeaderID;
	Callback callback;
	Object objData;
	
	public PacManClient(String electedLeaderID, Callback callback) {
		this.context = new ZContext();
		this.subscriber = context.createSocket(ZMQ.SUB);
		this.electedLeaderID = electedLeaderID;
		this.callback = callback;
	}
	
	public static void initializePacmanClient(String subscription, Callback callback){
		nodeInstance = new PacManClient(subscription, callback);
		nodeInstance.execute = true;
		nodeThread = new Thread(nodeInstance);
		nodeThread.start();
		
	}
	public static void destroy(){
		nodeInstance.subscriber.close();
		nodeInstance.context.destroy();
		nodeInstance.execute =false;
		nodeThread.interrupt();
		nodeThread = null;
	}
	
	

	public void run() {
		//
		this.subscriber.connect(Settings.getSubscriberURL());
		this.subscriber.subscribe(electedLeaderID.getBytes());
		while (this.execute){
			System.out.println("while true...");
			String topic = this.subscriber.recvStr();
			
			System.out.println("----- topic = " + topic);
            if (topic == null)
                continue;
            
            byte[] data = this.subscriber.recv();
            if(data!=null){
            objData = SerializationUtil.fromByteArrayToJava(data);
            if(objData instanceof ClientObject)
            this.callback.onMessage(data);
            }
		}
		
		
	}

	public static interface Callback {
		public void onMessage(byte[] data);
	}

}
