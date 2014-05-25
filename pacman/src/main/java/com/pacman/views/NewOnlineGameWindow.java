package com.pacman.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.pacman.controllers.GameEngine;
import com.pacman.pacmannetwork.Forwarder;
import com.pacman.pacmannetwork.PacmanServer;
import com.pacman.ring.node.SingleNode;
import com.pacman.utils.Settings;
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
    boolean flag;
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
            if(flag){
					Thread relay = new Thread(new Forwarder());
					relay.setDaemon(true);
					relay.start();
			}
			PacmanServer.initialize();
			System.out.println("start game engine");
			//TODO
			SwingUtilities.invokeLater(new GameEngine(2,flag));
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
					printToTextPane("Unable to start election. Please wait and retry..");
					
				}else{
					printToTextPane("Successfully started election");
			}
				  try {
						th.join();
					} catch (InterruptedException exp) {
						// TODO Auto-generated catch block
						exp.printStackTrace();
					}
				  String val = node.returnVal();
				  flag = node.isElected();
				  printToTextPane("Got the tcpValue: "+ val);
				  if(flag){
					  printToTextPane("You are the winner");
 
				  }
				  btnStart.setEnabled(true);
				  printToTextPane("Please start the game within the next few seconds");
				  
				  
		}});
		btnStartElection.setForeground(Color.BLACK);
		btnStartElection.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnStartElection.setBackground(new Color(40, 120, 255));
		btnStartElection.setBounds(43, 217, 128, 40);
		btnStartElection.setEnabled(false);
		contentPane.add(btnStartElection);
		

		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBounds(16, 16, 366, 189);
		textPane.setText("Click on connect to start and then start election...\n");
		contentPane.add(textPane);
		
		btnConnectToServer = new JButton("Connect");
		btnConnectToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean str= false;
				printToTextPane("Connecting to registry server ..");
				try {
					if(node.startConnection()){
						printToTextPane("Connect successfull");
					    btnStartElection.setEnabled(true);
					    btnConnectToServer.setEnabled(false);
					    str = true;
					    th.start();
					}
					else{
						printToTextPane("Could not connect");
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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