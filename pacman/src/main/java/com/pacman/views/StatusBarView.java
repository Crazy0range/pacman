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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public StatusBarView() {
		setBackground(new Color(40, 120, 255));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		

		lblPoints = new JLabel("  POINTS  ");
		lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPoints.setForeground(Color.WHITE);
		add(lblPoints);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		textField.setText("10000");
		textField.setEnabled(false);
		textField.setBackground(new Color(40, 120, 255));
		textField.setForeground(Color.WHITE);
		textField.setColumns(3);
		textField.setDisabledTextColor(Color.WHITE);
		textField.revalidate();

		lblLives = new JLabel("  LIVES  ");
		lblLives.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLives.setForeground(Color.WHITE);
		add(lblLives);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setDisabledTextColor(Color.WHITE);
		textField_2.setForeground(Color.WHITE);
		textField_2.setBackground(new Color(40, 120, 255));
		textField_2.setEnabled(false);
		textField_2.setColumns(3);

		lblTime = new JLabel("  TIME  ");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTime.setForeground(Color.WHITE);
		add(lblTime);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(5);
		textField_1.setForeground(Color.WHITE);
		textField_1.setDisabledTextColor(Color.WHITE);
		textField_1.setBackground(new Color(40, 120, 255));
		textField_1.setEnabled(false);
		textField_2.setColumns(3);

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
		textField.setText(points_str);
	}

	/**
	 * Set the remaining lives on status bar
	 * 
	 * @param lives
	 */
	public void setLives(int lives) {
		textField_2.setText(String.valueOf(lives));
	}
	
	public void setTime(int time){
		String time_str = String.valueOf(time);
		textField_1.setText("  " + time_str + "  ");
	}
	
	public void setRank(int rank){
		String rank_str = String.valueOf(rank);
		lblShowRank.setText(rank_str);
	}
}
