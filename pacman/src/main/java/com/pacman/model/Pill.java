package com.pacman.model;

import java.net.URL;

import javax.swing.ImageIcon;

import com.pacman.controllers.GameEngine;
import com.pacman.views.utils.AssetsManager;

/**
 *  Pacman Regular Pill
 *  @author     Yingjie Ma
 *  @version    1.0
 */
public class Pill extends StationaryObject {
	private static final long serialVersionUID = -5179415181539598746L;

	/**
	 * Creates a new pill
	 */
	public Pill() {

		super(new ImageIcon(AssetsManager.getResource(Pill.class, "img.png")));

	}
	
	/**
	 * Handles collision with pacman (kind of visitor pattern)
	 */
	@Override
	public void collideWith(Pacman pacman, GameEngine engine, int pacmanID) {
		engine.eatPill(pacman, this, pacmanID);
	}
}
