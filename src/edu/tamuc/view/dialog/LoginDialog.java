package edu.tamuc.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.tamuc.view.panel.LoginPanel;

public class LoginDialog extends JDialog {

	private static LoginDialog instance;
	
	private LoginDialog() {
		init();
	}

	public static LoginDialog getInstance() {
		if (instance == null) {
			instance = new LoginDialog();
		}
		return instance;
	}
	
	public void init() {

		this.setTitle("Login System");
		this.add(new LoginPanel());
		this.setSize(260, 160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
	}
	
	
}
