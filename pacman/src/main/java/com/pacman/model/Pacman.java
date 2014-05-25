package com.pacman.model;

import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.pacman.views.utils.AssetsManager;

/**
 *  Regular Pacman
 *  @author     Lidan Hifi
 *  @version    1.0
 */
public class Pacman extends ControllableObject implements Eater, Serializable {
	private static final long serialVersionUID = 8644963962014952922L;
	/* Pacman current direction */
	private Direction _directionX = Direction.RIGHT;
	// Pacman current direction, with up and down
	private Direction _directionReal = Direction.RIGHT;

	/**
	 * Creates a new Pacman with a given collisions map
	 * @param map collisions map
	 */
	public Pacman(Map map) {
		super(map);
		setPosition(map.getPacmanInitialPosition().x, map.getPacmanInitialPosition().y);
		setDirection(Direction.NONE);
	}
	
	@Override
	public void setDirection(Direction direction) {
		// change the pacman image direction (set the angle 90 or 270, or set the image to right or left)
		switch(direction) {
		case DOWN:
			if (_directionX == Direction.RIGHT)
				setSpriteAngle(90);
			else if (_directionX == Direction.LEFT)
				setSpriteAngle(270);
			_directionReal=Direction.DOWN;
			startAnimation();
			break;
		case LEFT:
			try {
				setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-left.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			_directionX = Direction.LEFT;
			setSpriteAngle(0);
			startAnimation();
			_directionReal=Direction.LEFT;
			break;
		case NONE:
			if (getSpriteImage() == null) {
				setDefaultIcon();
			}
			stopAnimation();
			_directionReal=Direction.NONE;
			break;
		case RIGHT:
			try {
				setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-right.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			_directionX = Direction.RIGHT;
			_directionReal=Direction.RIGHT;
			setSpriteAngle(0);
			startAnimation();
			break;
		case UP:
			if (_directionX == Direction.RIGHT)
				setSpriteAngle(270);
			else if (_directionX == Direction.LEFT)
				setSpriteAngle(90);
			_directionReal = Direction.UP;
			startAnimation();
			break;
		default:
			break;
		
		}
		
		super.setDirection(direction);
	}
	
	public Direction getDirection(){
		return _directionReal;
	}
	
	/**
	 * Pacman die - change the pacman image
	 */
	public void die() {
		try {
			setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-death.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * set the pacman to its default icon
	 */
	public void setDefaultIcon() {
		try {
			// default image
			setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-right.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determine if pacman can eat the given monster (using Visitor pattern)
	 * @param monster
	 * @return true if yes, otherwise not.
	 */
	public boolean eats(Monster monster) {
		return monster.eatenBy(this);
	}

	/**
	 * Regular pacman cannot eat weak monster
	 */
	@Override
	public boolean eats(WeakMonster monster) {
		return false;
	}

	/**
	 * Regular pacman cannot eat strong monster
	 */
	@Override
	public boolean eats(StrongMonster monster) {
		return false;
	}

}
