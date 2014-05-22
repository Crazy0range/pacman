package com.pacman.pacmanserver;

import java.io.Serializable;

public class ClientObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895280901013246381L;
	String value;

	public ClientObject(String value) {
		super();
		this.value = value;
	}
	 

}
