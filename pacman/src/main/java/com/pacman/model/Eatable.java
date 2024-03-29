package com.pacman.model;

/**
 *  [Visitor Pattern]
 *  Represents an object that can be eatable
 *  @author     Nikki Vinayan, Siyuan Liu, Yingjie Ma
 *  @version    1.0
 */
public interface Eatable {
	/**
	 * determines if the implemented object can be eaten by the given eater
	 * @param eater eater object
	 * @return true if yes, false if not
	 */
	public boolean eatenBy(Eater eater);
}
