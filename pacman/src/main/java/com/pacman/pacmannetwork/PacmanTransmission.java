package com.pacman.pacmannetwork;

import java.awt.Point;
import java.io.Serializable;

import com.pacman.model.Direction;
import com.pacman.model.Map;

public class PacmanTransmission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407743370069866137L;
	private Map map;
	private Direction dir;
	private Point position;
	public PacmanTransmission(){
		
	
				
	}
	public void setDirection(Direction dir){
		
		this.dir = dir;
	}
	public void setPosition(Point position){
		
		this.position= position;
	}
	

}
