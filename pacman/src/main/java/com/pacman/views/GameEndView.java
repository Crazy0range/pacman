package com.pacman.views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameEndView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3243548965275179389L;
	private int _point;
	private String _text;
	private JLabel lblNewLabel;

	public GameEndView() {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));

		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		add(lblGameOver, BorderLayout.CENTER);

		lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		_text = "<html>Your Points : 10000<br>You are the Winner!<br><br><br></html>";
		
		lblNewLabel.setText(_text);
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel, BorderLayout.SOUTH);

	}
	
	public void setPoints(int points){
		_text = "<html>Your Points : " + points +"<br>You are the Winner!<br><br><br></html>";
		lblNewLabel.setText(_text);
	}

}
