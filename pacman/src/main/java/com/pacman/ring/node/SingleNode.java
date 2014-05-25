package com.pacman.ring.node;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.zeromq.ZMQ;

import com.pacman.ring.model.Acknowledge;
import com.pacman.ring.model.ElectionMessages;
import com.pacman.ring.model.Response;
import com.pacman.utils.SerializationUtil;
import com.pacman.utils.Settings;
import com.pacman.utils.Variables;

public class SingleNode implements Runnable{
	public SingleNode(String port) {
		super();
		this.port = port;
		this.myUID = UUID.randomUUID();
		this.initialize();
	}

	String nTcpPoint=null;
	String tcpEndpoint;
	String port;
	UUID myUID;
	volatile Boolean electionStart;
	String leader;
	UUID elected_id = null;
	Boolean msgRec;
	Boolean elected = Boolean.FALSE;
	ElectionState state;
	boolean started;
	String leader_ip=null;

	public enum ElectionState {
		NON_PARTICIPANT, PARTICIPANT
	}

	public void initialize() {
		System.out.println("Initializing Proc " + this.myUID);
		this.state = ElectionState.NON_PARTICIPANT;
		this.msgRec = false;
	}

	public boolean startConnection() throws UnknownHostException {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		String localAddr = null;
		socket.connect(Settings.getRegistryServerURL());
		boolean connection=Boolean.FALSE;
		// try {
		//TODO change for distributed systems
		localAddr = InetAddress.getLocalHost().getHostAddress();
		//localAddr = localport
		// } catch (UnknownHostException e) {

		// e.printStackTrace();
		// }
		this.tcpEndpoint = "tcp://" + localAddr+":"+Variables.NodePort;
		System.out.println(this.tcpEndpoint);
		System.out.println("My UUID is :" + this.myUID);
		Response objectRes = new Response(this.myUID, this.tcpEndpoint,
				Variables.ALIVE);
		socket.send(SerializationUtil.fromJavaToByteArray(objectRes), 0);
		byte[] rawBytes = socket.recv(0);
		Object command = SerializationUtil.fromByteArrayToJava(rawBytes);
		if (command instanceof Acknowledge) {
			Acknowledge msg = (Acknowledge) command;
			System.out.println((msg).getPosition());
			if(msg.getResponse().equalsIgnoreCase(Variables.LIMIT)){
				connection = Boolean.FALSE;
			} else {
				connection = Boolean.TRUE;
			}
		}

		socket.close();
		context.term();
		return connection;

	}

	public void getNieghbour() {
		// TODO Auto-generated method stub
		ZMQ.Context context1 = ZMQ.context(1);
		ZMQ.Socket server = context1.socket(ZMQ.REP);
		//System.out.println("tcp://*:" + port);
		server.bind("tcp://*:5556");
		Object val;
		while (true) {
			System.out.println("In loop");
			byte[] nData = server.recv(0);
			System.out.println(nData);
			val = SerializationUtil.fromByteArrayToJava(nData);
			Acknowledge ack = new Acknowledge(Variables.ACK, 1);
			System.out.println("Got data!!!!");
			server.send(SerializationUtil.fromJavaToByteArray(ack),0);
			if ((val instanceof Response)
					&& (((Response) val).getRequestType()
							.equalsIgnoreCase(Variables.NNValue)))
				break;
		}
		this.nTcpPoint = ((Response) val).getTcpEndpoint();
		System.out.println(port + ": Got nieghbour value: " + nTcpPoint);
		server.close();
		context1.term();
	}

	public void processMessages(ElectionMessages em) {
		started = Boolean.TRUE;
		ElectionMessages.MessageType type = em.getMsgType();
		System.out.println("port:"+port+ " mssg "+ em.getMsgType());
		boolean rGreater = Boolean.FALSE;

		if (type == ElectionMessages.MessageType.ELECTION) {
			if (em.getmID() != null) {
				int compare = this.myUID.compareTo(em.getmID());
				System.out.println("port:"+port+ ": comaring values"+compare);
				if (compare != 0) {
					if (compare == 1) {
						// mID stores the largest election ID seen so far
						em.setmID(this.myUID);
					} else {
						rGreater = Boolean.TRUE;
					}
					if (state == ElectionState.NON_PARTICIPANT
							|| (state == ElectionState.PARTICIPANT && rGreater)) {
						System.out.println("Forwarding message");
						sendMessage(em);
						this.state = ElectionState.PARTICIPANT;
					}
				} else {
					ElectionMessages elected = new ElectionMessages(ElectionMessages.MessageType.ELECTED, this.myUID, this.myUID,this.tcpEndpoint);
					this.state = ElectionState.NON_PARTICIPANT;
					System.out.println(port+ ": Elected!!");
					System.out.println(port+":Recieved my election back");
					sendMessage(elected);
				}

			} else {
				em.setmID(this.myUID);
				System.out.println(port + "initial send");
				sendMessage(em);
				this.state = ElectionState.PARTICIPANT;
			}
		} else if (type == ElectionMessages.MessageType.ELECTED) {
			this.state = ElectionState.NON_PARTICIPANT;
			this.elected_id = em.getElectedUID();
			String val = em.getTcpPoint();
			if (val.endsWith(":5556")) {
				  val = val.substring(0, val.length() - 5);
				}
			this.leader_ip = val;
			this.elected = Boolean.TRUE;
			if (!elected_id.equals(this.myUID)) {
				sendMessage(em);
			} else{
				System.out.println(port +": I am the leader");
			}
		
			
		}
	}

	void sendMessage(ElectionMessages em) {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		// sending data to nieghbour TODO changed for distributed
		//socket.connect("tcp://*:" + this.nTcpPoint);
		socket.connect(this.nTcpPoint);
		socket.send(SerializationUtil.fromJavaToByteArray(em),0);
		System.out.println("Send data to right nieghbour");
		byte[] ackResp = socket.recv(0);
		Object resp = SerializationUtil.fromByteArrayToJava(ackResp);
		if (resp instanceof Acknowledge) {
			System.out.println("Recieved acknowledgement");
		}
	}

	public void listenforMessages() {
		ZMQ.Context context1 = ZMQ.context(1);
		ZMQ.Socket server = context1.socket(ZMQ.REP);
		System.out.println("tcp://*:" + port);
		server.bind("tcp://*:" + port);
		while(true) {
			byte[] nData = server.recv();
			server.send(SerializationUtil.fromJavaToByteArray(new Acknowledge("Sent",1)));
			Object msg = SerializationUtil.fromByteArrayToJava(nData);
			if (msg instanceof ElectionMessages) {
				System.out.println(port + ": listenForMssg true");
				this.processMessages((ElectionMessages) msg);
			}
	       if(this.elected_id!=null && this.state==ElectionState.NON_PARTICIPANT)
				break;
		}
		System.out.println("Stopped");
		server.close();
		context1.term();
		if(myUID.equals(elected_id)){
		Settings.setLeaderUrl("tcp://localhost");
		} else{
		Settings.setLeaderUrl(this.leader_ip);
		}
		Settings.setLeaderUUID(elected_id);
		
	}
	
	public void startElection(boolean startE) {
        if(elected_id == null)
		electionStart = startE;
     }

	public boolean detectStart() {
		boolean start = false;
		if (electionStart && nTcpPoint!=null && !elected && !this.started) {
			System.out.println("Starting election");
			
			ElectionMessages aMsg = new ElectionMessages(
					ElectionMessages.MessageType.ELECTION, null, this.myUID,null);
			sendMessage(aMsg);
			this.state = ElectionState.PARTICIPANT;
			electionStart = false;
			start= true;
		}
		return start;
	}
	
	public String returnVal(){
		return this.leader_ip;
	}

	public void run() {
		getNieghbour();
	    electionStart = (elected_id == null);
		
		//sleep(Integer.parseInt(port));
		Thread listener = new Thread(new Runnable() {
			public void run() {
				listenforMessages();
			}
		});
		
		listener.start();
		try {
			listener.join();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	
		if(!listener.isAlive()){
		  System.out.println("Thread is dead!!");	
		}
	}
	
	public String isElectionComplete(){
		String leaderVal =null;
		if (this.elected_id!=null && this.state==ElectionState.NON_PARTICIPANT){
			leaderVal = this.leader_ip;
		}
		return leaderVal;
	}
	
	private static void sleep(int i){
		try {
			System.out.println(i - 6000);
			Thread.sleep(TimeUnit.MILLISECONDS.convert(i - 6000, TimeUnit.SECONDS));
			System.out.println("Woken");
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	public boolean isElected() {
		if(myUID.equals(elected_id))
		return true;
		else 
		return false;
	}
}
