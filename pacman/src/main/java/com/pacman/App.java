package com.pacman;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import com.pacman.controllers.GameEngine;
import com.pacman.views.HomeWindow;

/**
 *  Pacman Game
 *  This game was an assignment for Object Oriented Software Design course, Ben Gurion University, July 2013
 *  @author     Lidan Hifi
 *  @version    1.0
 */
public class App 
{
    public static void main( String[] args )
    {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow frame = new HomeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
//    	SwingUtilities.invokeLater(new GameEngine());
    }
}
