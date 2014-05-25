package com.pacman.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main application frame, contains the game view panel
 * 
 * @author Lidan Hifi
 * @version 1.0
 */
public class GameWindow extends JFrame {
	private static final long serialVersionUID = -5539770173610616384L;
	private static Point _mouseDraggingCoordinates; // point for window dragging

 
	public GameWindow() {
		setResizable(false);
		if(HomeWindow.flag)
			setTitle("server");
		else
			setTitle("client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeUI();
	}

	/**
	 * set window in the screen center
	 */
	public void setWindowInScreenCenter() {
		Dimension size = getToolkit().getScreenSize();
		this.setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
	}

	private void initializeUI() {
		// set border layout
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		// mouse listeners for window dragging
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				_mouseDraggingCoordinates = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				_mouseDraggingCoordinates = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point currentPoint = e.getLocationOnScreen(); // get pointer
																// location
				setLocation(currentPoint.x - _mouseDraggingCoordinates.x,
						currentPoint.y - _mouseDraggingCoordinates.y); // set
																		// window
																		// location
			}
		});

		// event handler for window closing
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				Frame frame = (Frame) we.getSource();
				frame.dispose();
			}
		});
	}

	/**
	 * show view in the application window
	 * 
	 * @param view
	 *            - Any application view (for example {@link GameView})
	 */
	// Jason
	public void showView(JPanel gameView[], JPanel statusBarView[],
			int current_users) {
		// remove any components from the frame's content pane
		Container c = getContentPane();
		for (Component comp : c.getComponents()) {
			c.remove(comp);
		}

		// add the given gameView to the frame's center
		// Siyuan - status bar panel, put in NORTH of C
		JPanel statusBarPanel = new JPanel();
		statusBarPanel.setBackground(new Color(40, 120, 255));

		// Jason
		switch (current_users) {
		case 3:
			c.add(gameView[2], BorderLayout.WEST);
			statusBarPanel.add(statusBarView[2], BorderLayout.WEST);
		case 2:
			c.add(gameView[1], BorderLayout.EAST);
			statusBarPanel.add(statusBarView[1],BorderLayout.EAST);
		case 1:
			c.add(gameView[0], BorderLayout.CENTER);
			statusBarPanel.add(statusBarView[0],BorderLayout.CENTER);
			break;
		}

		c.add(statusBarPanel, BorderLayout.NORTH);
		// c.add(gameView1,BorderLayout.EAST);
		for (int i = 0; i < current_users; i++) {
			gameView[i].updateUI();
			gameView[i].setVisible(true);
			statusBarView[i].updateUI();
			statusBarView[i].setVisible(true);
		}
		// gameView1.updateUI();
//		statusBarView.updateUI();

		// set the gameView visibility and repaint the application window
		// gameView.setVisible(true);
		// gameView1.setVisible(true);
	
		pack();
		repaint();
	}


}
