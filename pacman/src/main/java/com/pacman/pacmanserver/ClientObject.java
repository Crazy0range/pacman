package com.pacman.pacmanserver;

import java.io.Serializable;

import com.pacman.model.Pacman;

public class ClientObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895280901013246381L;
	String value;
	Pacman pacman;

	public ClientObject(String value,Pacman pacman) {
		super();
		this.value = value;
		this.pacman = pacman;
	}
	 

}
