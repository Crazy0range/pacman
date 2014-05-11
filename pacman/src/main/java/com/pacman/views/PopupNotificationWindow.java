package com.pacman.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 * @author Siyuan Liu
 *
 */
public class PopupNotificationWindow extends JFrame {

	private JPanel contentPane;
	private static JTextPane textPane;

	/**
	 * Create the frame. General purpose pop up window, use static method
	 * notify(String) to send popups.
	 * @author siyuanliu
	 */
	public PopupNotificationWindow() {
		setTitle("Pacman");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWhiteboardName = new JLabel("Notification");
		lblWhiteboardName.setForeground(new Color(255, 255, 255));
		lblWhiteboardName.setBounds(135, 20, 150, 38);
		contentPane.add(lblWhiteboardName);
		lblWhiteboardName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));

		textPane = new JTextPane();
		textPane.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		textPane.setBounds(20, 61, 339, 104);
		textPane.setEditable(false);
		contentPane.add(textPane);

		JButton btnCancel = new JButton("OK");
		btnCancel.setBounds(220, 200, 140, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(255, 215, 0));
	}

	public static void notify(String text) {
		PopupNotificationWindow frame = new PopupNotificationWindow();
		frame.setVisible(true);
		textPane.setText(text);
	}
}