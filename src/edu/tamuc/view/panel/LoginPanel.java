package edu.tamuc.view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import edu.tamuc.action.LoginAction;

import java.awt.Color;

public class LoginPanel extends JPanel {
	private JTextField username;
	private JPasswordField pwd;
	private JLabel infoLabel;
	private LoginAction action = new LoginAction(this);

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		infoLabel = new JLabel("Invalid user/password!");
		infoLabel.setForeground(Color.RED);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVisible(false);
		topPanel.add(infoLabel);
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new GridLayout(2,2));
		
		username = new JTextField("",10); 
		pwd = new JPasswordField("",10);
		JLabel usrlabel = new JLabel("User Name: ");
		JLabel pwdlabel = new JLabel("Password: ");
		
		JPanel usrLabelPanel = new JPanel();
		usrLabelPanel.add(usrlabel);
		framePanel.add(usrLabelPanel);
		
		JPanel userFieldPanel =  new JPanel();
		userFieldPanel.add(username);
		framePanel.add(userFieldPanel);
		
		JPanel pwdLabelPanel = new JPanel();
		pwdLabelPanel.add(pwdlabel);
		framePanel.add(pwdLabelPanel);
		
		JPanel pwdFieldPanel =  new JPanel();
		pwdFieldPanel.add(pwd);
		framePanel.add(pwdFieldPanel);
		
		this.add(framePanel, BorderLayout.CENTER);
		
		JPanel buttomPanel = new JPanel();
        buttomPanel.add(createButton("Sign In"));
        buttomPanel.add(createButton("Reset"));
        this.add(buttomPanel, BorderLayout.SOUTH);
		

	}
	
	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}

	public JButton createButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(action);
		return button;
	}
	
	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public void setPwd(JPasswordField pwd) {
		this.pwd = pwd;
	}

	public JPasswordField getPwd() {
		return pwd;
	}

}
