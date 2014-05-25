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
	private Direction dir;
	private Point position;
	private int point;
	public PacmanTransmission(){
		
	
				
	}
	public void setDirection(Direction dir){
		
		this.dir = dir;
	}
	public void setPosition(Point position){
		
		this.position= position;
	}
	public Direction getDirection(){
		
		return this.dir;
	}
	public Point getPosition(){
		
		return this.position;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	

}
