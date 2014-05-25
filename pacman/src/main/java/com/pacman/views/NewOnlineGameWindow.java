package com.pacman.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pacman.utils.UserProfile;


/**
 * @author Siyuan Liu
 *
 */
public class NewOnlineGameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tfBoardName;

	/**
	 * Used for creating a new WhiteBord window.
	 */
	public NewOnlineGameWindow(UserProfile uPrf) {
		setTitle("New Online Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWhiteboardName = new JLabel("Game Name");
		lblWhiteboardName.setForeground(new Color(255, 255, 255));
		lblWhiteboardName.setBounds(20, 20, 150, 38);
		contentPane.add(lblWhiteboardName);
		lblWhiteboardName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));

		tfBoardName = new JTextField();
		tfBoardName.setBounds(172, 20, 160, 32);
		contentPane.add(tfBoardName);
		tfBoardName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		tfBoardName.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				RegisteryNode hostNode = new RegisteryNode(tfBoardName
//						.getText(), Variables.HOST);
//				hostNode.startHostConnection();
//				String boardName = tfBoardName.getText();
//				WhiteBoardWindow whiteBoardWindow = new WhiteBoardWindow(
//						boardName, true, boardName);
//				UserProfile uPrf = Settings.getUserProfile();
//				WhiteBoardWindow.addManager(uPrf);
//				whiteBoardWindow.setVisible(true);
//				dispose();
			}
		});
		btnStart.setBounds(206, 133, 140, 40);
		contentPane.add(btnStart);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnStart.setBackground(new Color(40, 120, 255));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(206, 189, 140, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(40, 120, 255));
	}

}