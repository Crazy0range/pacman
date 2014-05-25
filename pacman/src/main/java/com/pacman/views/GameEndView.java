package com.pacman.views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
	private JTextArea taResults;

	public GameEndView() {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));

		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		add(lblGameOver, BorderLayout.CENTER);

		taResults = new JTextArea();
//		taResults.setHorizontalAlignment(SwingConstants.CENTER);

		// _text =
		// "<html>Your Points : 10000\nYou are the Winner!\n\n\n\n\n\n</html>";
		//
		// taResults.setText(_text);
		taResults.setForeground(Color.WHITE);
		taResults.setEnabled(false);
		taResults.setBackground(Color.BLACK);
		taResults.setDisabledTextColor(Color.WHITE);
		
		JPanel resultpanel = new JPanel();
		resultpanel.add(taResults, BorderLayout.NORTH);
		resultpanel.setBackground(Color.BLACK);
		add(resultpanel, BorderLayout.SOUTH);
		

	}

	public void setPoints(int points, int lives) {
		int bonus_points = 1000 * lives;
		_text = "Remaining Lives : " + lives + "\nBonus Points:"
				+ lives + "*1000=" + bonus_points + "\nYour Points : "
				+ points + "+" + bonus_points + "=" + (points + bonus_points)
				+ "\nYou are the Winner!\n\n\n";
		taResults.setText(_text);
	}

}
