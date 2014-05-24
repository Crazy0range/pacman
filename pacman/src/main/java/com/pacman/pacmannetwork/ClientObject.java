package com.pacman.pacmannetwork;

import java.io.Serializable;

import com.pacman.model.Pacman;

public class ClientObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895280901013246381L;
	String value;
	PacmanTransmission pacman;

	public ClientObject(String value,PacmanTransmission pacman) {
		super();
		this.value = value;
		this.pacman = pacman;
	}
	 

}
