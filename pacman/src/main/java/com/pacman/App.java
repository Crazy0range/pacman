package com.pacman;

import java.awt.EventQueue;

import com.pacman.views.HomeWindow;

/**
 * Pacman Game This game was an assignment for Distributed Algorithms course.
 * 
 * @author Nikki Vinayan, Siyuan Liu, Yingjie Ma
 * @version 1.0
 */
public class App {
	public static void main(String[] args) {
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

	}
}
