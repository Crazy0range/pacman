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
	String subscription;
	Callback callback;
	Object objData;
	
	
	public PacManClient(String electedLeaderID, Callback callback) {
		this.context = new ZContext();
		this.subscriber = context.createSocket(ZMQ.SUB);
		this.subscription = electedLeaderID;
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
		    //TODO change made to be dist
		    //this.subscriber.connect(Settings.getSubscriberURL());
		    this.subscriber.connect(Settings.getDistSubscriberURL());
			this.subscriber.subscribe(subscription.getBytes());
			while (this.execute){
				System.out.println("while true...");
				byte[] topic = this.subscriber.recv();
				
				System.out.println("----- topic = " + topic);
	            if (topic == null)
	                continue;
	            
	            byte[] data = this.subscriber.recv();
	            this.callback.onMessage(data);
		}
		
		
	}

	public static interface Callback {
		public void onMessage(byte[] data);
	}

}
