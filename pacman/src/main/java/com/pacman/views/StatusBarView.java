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
import javax.swing.JTextField;

/**
 * Status Bar View
 * 
 * @author siyuanliu
 * 
 */
public class StatusBarView extends JPanel {

	private JLabel lblPoints;
	private JLabel lblLives;
	private JLabel lblTime;
	private JLabel lblRank;
	private JLabel lblShowRank;
	private JTextField points;
	private JTextField lives;
	private JTextField time;

	public StatusBarView() {
		setBackground(new Color(40, 120, 255));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		

		lblPoints = new JLabel("  POINTS  ");
		lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPoints.setForeground(Color.WHITE);
		add(lblPoints);
		
		points = new JTextField();
		add(points);
		points.setColumns(10);
		points.setText("10000");
		points.setEnabled(false);
		points.setBackground(new Color(40, 120, 255));
		points.setForeground(Color.WHITE);
		points.setColumns(3);
		points.setDisabledTextColor(Color.WHITE);
		points.revalidate();

		lblLives = new JLabel("  LIVES  ");
		lblLives.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLives.setForeground(Color.WHITE);
		add(lblLives);
		
		lives = new JTextField();
		add(lives);
		lives.setDisabledTextColor(Color.WHITE);
		lives.setForeground(Color.WHITE);
		lives.setBackground(new Color(40, 120, 255));
		lives.setEnabled(false);
		lives.setColumns(3);

		lblTime = new JLabel("  TIME  ");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTime.setForeground(Color.WHITE);
		add(lblTime);
		
		time = new JTextField();
		add(time);
		time.setColumns(5);
		time.setForeground(Color.WHITE);
		time.setDisabledTextColor(Color.WHITE);
		time.setBackground(new Color(40, 120, 255));
		time.setEnabled(false);
		lives.setColumns(3);

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
	public void setPoints(int _points) {
		String points_str = String.valueOf(_points);
		points.setText(points_str);
	}

	/**
	 * Set the remaining lives on status bar
	 * 
	 * @param lives
	 */
	public void setLives(int _lives) {
		lives.setText(String.valueOf(_lives));
	}
	
	public void setTime(int _time){
		String time_str = String.valueOf(_time);
		time.setText("  " + time_str + "  ");
	}
	
	public void setRank(int rank){
		String rank_str = String.valueOf(rank);
		lblShowRank.setText(rank_str);
	}
}
