/**
 * 
 */
package com.pacman.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Status Bar View
 * 
 * @author siyuanliu
 * 
 */
public class StatusBarView extends JPanel {

	private JLabel lblPoints;
	private JLabel lblShowPoints;
	private JLabel lblLives;
	private JLabel lblShowLive_0;
	private JLabel lblShowLive_1;
	private JLabel lblShowLive_2;
	private JLabel lblTime;
	private JLabel lblShowTime;
	private JLabel lblRank;
	private JLabel lblShowRank;

	public StatusBarView() {
		setBackground(new Color(40, 120, 255));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		

		lblPoints = new JLabel("  POINTS  ");
		lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPoints.setForeground(Color.WHITE);
		add(lblPoints);

		lblShowPoints = new JLabel("10000");
		lblShowPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblShowPoints.setForeground(Color.WHITE);
		add(lblShowPoints);

		lblLives = new JLabel("  LIVES  ");
		lblLives.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLives.setForeground(Color.WHITE);
		add(lblLives);

		lblShowLive_0 = new JLabel("O  ");
		lblShowLive_0.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblShowLive_0.setForeground(Color.WHITE);
		add(lblShowLive_0);

		lblShowLive_1 = new JLabel("O  ");
		lblShowLive_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblShowLive_1.setForeground(Color.WHITE);
		add(lblShowLive_1);

		lblShowLive_2 = new JLabel("O  ");
		lblShowLive_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblShowLive_2.setForeground(Color.WHITE);
		add(lblShowLive_2);

		lblTime = new JLabel("  TIME  ");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTime.setForeground(Color.WHITE);
		add(lblTime);

		lblShowTime = new JLabel("  999  ");
		lblShowTime.setForeground(Color.WHITE);
		lblShowTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblShowTime);

		lblRank = new JLabel("  RANK  ");
		lblRank.setForeground(Color.WHITE);
		lblRank.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblRank);

		lblShowRank = new JLabel("1");
		lblShowRank.setForeground(Color.WHITE);
		lblShowRank.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblShowRank);
	}

	/**
	 * Set the player points on status bar
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		String points_str = String.valueOf(points);
		lblShowPoints.setText(points_str);
	}

	/**
	 * Set the remaining lives on status bar
	 * 
	 * @param lives
	 */
	public void setLives(int lives) {
		switch (lives) {
		case 0:
			lblShowLive_0.setVisible(false);
			lblShowLive_1.setVisible(false);
			lblShowLive_1.setVisible(false);
			break;
		case 1:
			lblShowLive_0.setVisible(true);
			lblShowLive_1.setVisible(false);
			lblShowLive_1.setVisible(false);
			break;
		case 2:
			lblShowLive_0.setVisible(true);
			lblShowLive_1.setVisible(true);
			lblShowLive_1.setVisible(false);
			break;
		case 3:
			lblShowLive_0.setVisible(true);
			lblShowLive_1.setVisible(true);
			lblShowLive_1.setVisible(true);
			break;
		default:
			lblShowLive_0.setVisible(true);
			lblShowLive_1.setVisible(true);
			lblShowLive_1.setVisible(true);
		}
	}
	
	public void setTime(int time){
		String time_str = String.valueOf(time);
		lblShowTime.setText("  " + time_str + "  ");
	}
	
	public void setRank(int rank){
		String rank_str = String.valueOf(rank);
		lblShowRank.setText(rank_str);
	}
}
