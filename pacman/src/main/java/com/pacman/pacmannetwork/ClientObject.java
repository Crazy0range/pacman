package com.pacman.pacmannetwork;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pacman.model.Monster;
import com.pacman.model.Pacman;

public class ClientObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895280901013246381L;
	public String value;
	public PacmanTransmission pacman;
	
	//try to transport monsters
//	public List<Monster> _monsters = new ArrayList<Monster>(); 

	public ClientObject(String value,PacmanTransmission pacman) {
		super();
		this.value = value;
		this.pacman = pacman;
//		this._monsters = _monsters;
	}
	 

}
