package com.pacman.ring.model;

import java.awt.TrayIcon.MessageType;
import java.io.Serializable;
import java.util.UUID;

public class ElectionMessages implements Serializable{

	private MessageType msgType;
    private UUID electedUID;
    private UUID mID;
    private String tcpPoint;
    
    public enum MessageType {
        ELECTION, ELECTED
}
    
   public ElectionMessages(MessageType msgType, UUID electedUID, UUID mID, String tcpPoint) {
		super();
		this.msgType = msgType;
		this.electedUID = electedUID;
		this.mID = mID;
		this.tcpPoint= tcpPoint;
	}

	public String getTcpPoint() {
	return tcpPoint;
}

public void setTcpPoint(String tcpPoint) {
	this.tcpPoint = tcpPoint;
}

	public ElectionMessages() {
		super();
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public UUID getElectedUID() {
		return electedUID;
	}

	public void setElectedUID(UUID electedUID) {
		this.electedUID = electedUID;
	}

	public UUID getmID() {
		return mID;
	}

	public void setmID(UUID mID) {
		this.mID = mID;
	}
    
    
    
}
