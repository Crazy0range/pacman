package com.pacman.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.pacman.utils.UserProfile;

/**
 * @author Siyuan Liu
 * 
 */
public class RegistryServerWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnStart;
	private JButton btnCancel;
	private JTextPane textPane;

	/**
	 * Used for creating a new WhiteBord window.
	 */
	public RegistryServerWindow(UserProfile uPrf) {
		setTitle("New Online Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStart = new JButton("Start Game");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textPane.setText(textPane.getText() + "Starting server...\n");
				//TODO Your server start code here.
			}
		});
		btnStart.setBounds(136, 217, 128, 40);
		contentPane.add(btnStart);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnStart.setBackground(new Color(40, 120, 255));

		btnCancel = new JButton("Stop");
		btnCancel.setBounds(268, 217, 128, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(40, 120, 255));

		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBounds(16, 16, 366, 189);
		textPane.setText("Server not started.\n");
		contentPane.add(textPane);

	}
}