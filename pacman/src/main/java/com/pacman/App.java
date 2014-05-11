package com.pacman;

import javax.swing.SwingUtilities;

import com.pacman.controllers.GameEngine;

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
    	SwingUtilities.invokeLater(new GameEngine());
    }
}
