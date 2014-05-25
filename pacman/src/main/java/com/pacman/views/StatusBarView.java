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
	private JTextField tfPoints;
	private JTextField tfLives;
	private JTextField tfTime;
	private JTextField tfRank;

	public StatusBarView() {
		setBackground(new Color(40, 120, 255));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		lblPoints = new JLabel(" POINTS ");
		lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPoints.setForeground(Color.WHITE);
		add(lblPoints);
		
		tfPoints = new JTextField();
		add(tfPoints);
		tfPoints.setColumns(3);
		tfPoints.setText("1000");
		tfPoints.setBackground(new Color(40, 120, 255));
		tfPoints.setEnabled(false);
		tfPoints.setForeground(Color.WHITE);
		tfPoints.setColumns(6);
		tfPoints.setDisabledTextColor(Color.WHITE);
		tfPoints.setHorizontalAlignment(JTextField.RIGHT);
		tfPoints.revalidate();


		lblLives = new JLabel("  LIVES  ");
		lblLives.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLives.setForeground(Color.WHITE);
		add(lblLives);
		
		tfLives = new JTextField();
		add(tfLives);
		tfLives.setDisabledTextColor(Color.WHITE);
		tfLives.setForeground(Color.WHITE);
		tfLives.setBackground(new Color(40, 120, 255));
		tfLives.setEnabled(false);
		tfLives.setHorizontalAlignment(JTextField.RIGHT);
		tfLives.setColumns(3);

		lblTime = new JLabel("  TIME  ");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTime.setForeground(Color.WHITE);
		add(lblTime);
		
		tfTime = new JTextField();
		add(tfTime);
		tfTime.setColumns(3);
		tfTime.setForeground(Color.WHITE);
		tfTime.setDisabledTextColor(Color.WHITE);
		tfTime.setBackground(new Color(40, 120, 255));
		tfTime.setHorizontalAlignment(JTextField.RIGHT);
		tfTime.setEnabled(false);

		lblRank = new JLabel("  RANK  ");
		lblRank.setForeground(Color.WHITE);
		lblRank.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblRank);

		tfRank = new JTextField();
		tfRank.setForeground(Color.WHITE);
		tfRank.setColumns(1);
		tfRank.setForeground(Color.WHITE);
		tfRank.setDisabledTextColor(Color.WHITE);
		tfRank.setBackground(new Color(40, 120, 255));
		tfRank.setHorizontalAlignment(JTextField.RIGHT);
		tfRank.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		tfRank.setEnabled(false);
		add(tfRank);
	}

	/**
	 * Set the player points on status bar
	 * 
	 * @param points
	 */
	public void setPoints(int _points) {
		String points_str = String.valueOf(_points);
		tfPoints.setText(points_str);
	}

	/**
	 * Set the remaining lives on status bar
	 * 
	 * @param lives
	 */
	public void setLives(int _lives) {
		tfLives.setText(String.valueOf(_lives));
	}
	
	public void setTime(int _time){
		String time_str = String.valueOf(_time);
		tfTime.setText(time_str);
		if(_time <= 10){
			tfTime.setDisabledTextColor(Color.RED);
		} else {
			tfTime.setDisabledTextColor(Color.WHITE);
		}
		if(_time < 0){
			tfTime.setText("0");
		}
	}
	
	public void setRank(int rank){
		String rank_str = String.valueOf(rank);
		tfRank.setText(rank_str);
	}
	
	public int getPoints(){
		int i = Integer.parseInt(tfPoints.getText());
		return i;
	}
}
