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

import com.pacman.ring.node.SingleNode;
import com.pacman.utils.UserProfile;
import com.pacman.utils.Variables;

/**
 * @author Siyuan Liu
 * 
 */
public class NewOnlineGameWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnStart;
	private JButton btnCancel;
	private JButton btnStartElection;
	private JTextPane textPane;
	private JButton btnConnectToServer;
	SingleNode node;
	Thread th;

	/**
	 * Used for creating a new WhiteBord window.
	 */
	public NewOnlineGameWindow(UserProfile uPrf) {
		setTitle("New Online Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		node = new SingleNode(Variables.NodePort);
		th = new Thread(node);
		

		btnStart = new JButton("Start Game");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// RegisteryNode hostNode = new RegisteryNode(tfBoardName
				// .getText(), Variables.HOST);
				// hostNode.startHostConnection();
				// String boardName = tfBoardName.getText();
				// WhiteBoardWindow whiteBoardWindow = new WhiteBoardWindow(
				// boardName, true, boardName);
				// UserProfile uPrf = Settings.getUserProfile();
				// WhiteBoardWindow.addManager(uPrf);
				// whiteBoardWindow.setVisible(true);
				// dispose();
			}
		});
		btnStart.setBounds(217, 217, 128, 40);
		contentPane.add(btnStart);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnStart.setBackground(new Color(40, 120, 255));
		btnStart.setEnabled(false);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(217, 269, 128, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(40, 120, 255));

		btnStartElection = new JButton("Start Election");
		btnStartElection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				node.startElection(true);
				if(!node.detectStart()){
					printToTextPane("Unable to start election. Please wait");
				}else{
					printToTextPane("Successfully started election");
				}
			}
		});
		btnStartElection.setForeground(Color.BLACK);
		btnStartElection.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnStartElection.setBackground(new Color(40, 120, 255));
		btnStartElection.setBounds(43, 217, 128, 40);
		btnStart.setEnabled(false);
		contentPane.add(btnStartElection);
		

		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBounds(16, 16, 366, 189);
		textPane.setText("Waiting for leader...\n");
		contentPane.add(textPane);
		
		btnConnectToServer = new JButton("Connect");
		btnConnectToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printToTextPane("Connecting to registry server ..");
				if(node.startConnection()){
					printToTextPane("Connect successfull");
                    th.start();
				}
				
			}
		});
		btnConnectToServer.setForeground(Color.BLACK);
		btnConnectToServer.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnConnectToServer.setBackground(new Color(40, 120, 255));
		btnConnectToServer.setBounds(43, 269, 128, 40);
		contentPane.add(btnConnectToServer);

	}

	private void printToTextPane(String s) {
		String origin = textPane.getText();
		if (origin.split("\n").length < 9)
			textPane.setText(origin + s + "\n");
		else {
			textPane.setText(origin.substring(origin.indexOf('\n') + 1) + s
					+ "\n");
		}
	}
}