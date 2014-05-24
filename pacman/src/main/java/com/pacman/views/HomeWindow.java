package com.pacman.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.pacman.controllers.GameEngine;
import com.pacman.utils.IPAddrCheck;
import com.pacman.utils.UserProfile;

/**
 * @author Siyuan Liu, Nikki Vinayan, Yingjie Ma The home window for Pacman game
 *         server and client.
 */
public class HomeWindow extends JFrame {

	private JPanel contentPane;
	private static JLabel lblABigWelcome;
	private static JLabel lblDiscription;
	private static JLabel lblNickname;
	private static JButton btnNewButton;
	private static JButton btnJoinWhiteboard;
	private JButton btnSetting;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnOk;
	private MyProfileWindow myProfileWindow;
	private NewOnlineGameWindow newOnlineGameWindow;
	private JoinGameWindow joinGameWindow;

	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow frame = new HomeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * The home window for Pacman server and client.
	 */
	public HomeWindow() {
		setTitle("PacManGame");
		final UserProfile up = new UserProfile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("Online Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOnlineGameWindow = new NewOnlineGameWindow(up);
				newOnlineGameWindow.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 215, 0));
		btnNewButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnNewButton.setBounds(20, 172, 176, 40);
		contentPane.add(btnNewButton);

		btnJoinWhiteboard = new JButton("Join Game");
		btnJoinWhiteboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// RegisteryNode clientNode = new RegisteryNode("Client",
				// Variables.JOIN);
				// TODO: Change the argument from null to clientNode.
				joinGameWindow = new JoinGameWindow(null, up);
				joinGameWindow.setVisible(true);
			}
		});
		btnJoinWhiteboard.setForeground(Color.BLACK);
		btnJoinWhiteboard.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnJoinWhiteboard.setBackground(new Color(255, 215, 0));
		btnJoinWhiteboard.setBounds(20, 222, 176, 40);
		contentPane.add(btnJoinWhiteboard);

		JButton btnMyProfile = new JButton("My Profile");
		btnMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myProfileWindow = new MyProfileWindow();
				myProfileWindow.setVisible(true);
			}
		});
		btnMyProfile.setForeground(Color.BLACK);
		btnMyProfile.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnMyProfile.setBackground(new Color(255, 215, 0));
		btnMyProfile.setBounds(20, 272, 176, 40);
		contentPane.add(btnMyProfile);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setForeground(Color.BLACK);
		btnQuit.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnQuit.setBackground(new Color(255, 215, 0));
		btnQuit.setBounds(20, 370, 176, 40);
		contentPane.add(btnQuit);

		lblABigWelcome = new JLabel("<html>A Big Welcome to Pacman World! \r\n"
				+ "<br> Haven't set up your profile yet?\r\n"
				+ "<br> Just click \"My Profile\" to get Started!</html>");
		lblABigWelcome.setVerticalAlignment(SwingConstants.TOP);
		lblABigWelcome.setForeground(Color.WHITE);
		lblABigWelcome.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblABigWelcome.setBounds(270, 120, 327, 160);
		contentPane.add(lblABigWelcome);

		lblNickname = new JLabel("NickName");
		lblNickname.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setBounds(618, 17, 100, 21);
		lblNickname.setVisible(false);
		contentPane.add(lblNickname);

		lblDiscription = new JLabel("Discription");
		lblDiscription.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblDiscription.setForeground(Color.WHITE);
		lblDiscription.setBounds(618, 38, 114, 30);
		lblDiscription.setVisible(false);
		contentPane.add(lblDiscription);

		btnSetting = new JButton("Settings");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setVisible(true);
				lblNewLabel.setVisible(true);
				btnOk.setVisible(true);
				textField.requestFocus();
			}
		});
		btnSetting.setForeground(Color.BLACK);
		btnSetting.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnSetting.setBackground(new Color(255, 215, 0));
		btnSetting.setBounds(20, 320, 176, 40);
		contentPane.add(btnSetting);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textField.setBounds(288, 322, 138, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblNewLabel.setBounds(206, 327, 79, 27);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IPAddrCheck.isValidIP(textField.getText())) {
					String serverIP = "tcp://" + textField.getText();
					// Settings.getInstance().put("RegistryURL",
					// serverIP.trim());
					// WBServer.initialize();
					lblNewLabel.setVisible(false);
					textField.setVisible(false);
					btnOk.setVisible(false);
				} else
					PopupNotificationWindow.notify("Invalid IP address.");
			}
		});
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnOk.setBackground(new Color(255, 215, 0));
		btnOk.setBounds(447, 321, 56, 40);
		contentPane.add(btnOk);

		//TODO test!!!!!
		JButton btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new GameEngine(3));
				SwingUtilities.invokeLater(new GameEngine(2));
			}
		});
		btnSinglePlayer.setForeground(Color.BLACK);
		btnSinglePlayer.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnSinglePlayer.setBackground(new Color(255, 215, 0));
		btnSinglePlayer.setBounds(20, 120, 176, 40);
		contentPane.add(btnSinglePlayer);
		btnOk.setVisible(false);

		if (up.loadProfile()) {
			lblABigWelcome.setText("Welcome back, " + up.getNickname() + "!");
			lblNickname.setText(up.getNickname());
			lblDiscription.setText(up.getDiscription());
			lblNickname.setVisible(true);
			lblDiscription.setVisible(true);
		} else {
			btnNewButton.setEnabled(false);
			btnJoinWhiteboard.setEnabled(false);
		}
		// Settings.setUserProfile(up);
	}

	public static void updateProfile(UserProfile up) {
		if (up == null) {
			btnNewButton.setEnabled(true);
			btnJoinWhiteboard.setEnabled(true);
		} else {
			lblABigWelcome.setText("Welcome back, " + up.getNickname() + "!");
			lblNickname.setText(up.getNickname());
			lblDiscription.setText(up.getDiscription());
			lblNickname.setVisible(true);
			lblDiscription.setVisible(true);
			btnNewButton.setEnabled(true);
			btnJoinWhiteboard.setEnabled(true);
		}
	}
}