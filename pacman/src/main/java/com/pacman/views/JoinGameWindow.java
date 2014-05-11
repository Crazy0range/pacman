package com.pacman.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pacman.utils.UserProfile;
/**
 * @author Siyuan Liu
 *
 */
public class JoinGameWindow extends JFrame {

	private JPanel contentPane;
//	private DefaultListModel<String> hostListModel = new DefaultListModel<String>();
//	final JList<String> list;
	private UserProfile uProfile;
	private JLabel lblPleaseSelectA;

	/**
	 * Create the frame.
	 * 
	 * @param userName
	 * 
	 * Window for Joining WhiteBoard
	 */
	public JoinGameWindow(List<String> hosts, UserProfile userProfile) {
		setTitle("Join Online Game");
		this.uProfile = userProfile;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWhiteboardName = new JLabel("Select a WhiteBoard");
		lblWhiteboardName.setForeground(new Color(255, 255, 255));
		lblWhiteboardName.setBounds(20, 20, 164, 38);
		contentPane.add(lblWhiteboardName);
		lblWhiteboardName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));

		JButton btnStart = new JButton("Join");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String wbname = list.getSelectedValue();
//				if (wbname != null) {
//					ReqObject reqObj = MessageUtil.getJoinReqObj(wbname,
//							uProfile);
//					System.out.println("send");
//					WBServer.sendAdminObj(reqObj);
//					WhiteBoardWindow WBWindow = new WhiteBoardWindow(wbname,
//							false, uProfile.getNickname());
//					WBWindow.setVisible(true);
//					dispose();
//				} else
//					lblPleaseSelectA.setVisible(true);
			}
		});
		btnStart.setBounds(204, 309, 140, 40);
		contentPane.add(btnStart);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnStart.setBackground(new Color(255, 215, 0));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(204, 365, 140, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(255, 215, 0));

//		list = new JList<String>();
//		list.setBounds(30, 68, 314, 195);
//		list.setModel(hostListModel);
//		if (hosts != null) {
//			System.out.println(hosts.get(0));
//			addHosts(hosts);
//		} else {
//			hostListModel.addElement("No hosted boards");
//			btnStart.setEnabled(false);
//		}
//		contentPane.add(list);

		lblPleaseSelectA = new JLabel("Please select a WhiteBoard");
		lblPleaseSelectA.setForeground(Color.ORANGE);
		lblPleaseSelectA.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblPleaseSelectA.setBounds(20, 259, 216, 38);
		contentPane.add(lblPleaseSelectA);
		lblPleaseSelectA.setVisible(false);
	}

//	private void addHosts(List<String> hosts) {
//		Iterator<String> iterator = hosts.iterator();
//		while (iterator.hasNext()) {
//			hostListModel.addElement(iterator.next());
//		}
//	}
}