package com.pacman.model;

import javax.swing.ImageIcon;

import com.pacman.controllers.GameEngine;
import com.pacman.views.utils.AssetsManager;

/**
 *  Super Pill
 *  @author     Nikki Vinayan, Siyuan Liu, Yingjie Ma
 *  @version    1.0
 */
public class SuperPill extends Pill {
	private static final long serialVersionUID = -1374549731502119905L;

	/**
	 * Creates a new super pill
	 */
	public SuperPill() {
		super();
		setIcon(new ImageIcon(AssetsManager.getResource(SuperPill.class, "img.png")));
	}
	
	/**
	 * Handles collision with pacman (kind of visitor pattern)
	 */
	@Override
	public void collideWith(Pacman pacman, GameEngine engine, int pacmanID) {
		engine.eatSuperPill(pacman, this, pacmanID);
	}
}
