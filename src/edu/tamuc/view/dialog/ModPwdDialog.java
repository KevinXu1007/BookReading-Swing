package edu.tamuc.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.tamuc.action.PasswordAction;
import edu.tamuc.bean.UserInfo;


public class ModPwdDialog extends JDialog {

	private static ModPwdDialog instance;
	private UserInfo user;
	private JPasswordField oldPwd;
	private JPasswordField newPwd;
	private JPasswordField confirmPwd;
	private JLabel infoLabel;
	private PasswordAction action = new PasswordAction(this);
	

	private ModPwdDialog() {
		init();
	}

	public static ModPwdDialog getInstance() {
		if (instance == null) {
			instance = new ModPwdDialog();
		}
		return instance;
	}
	
	public void init() {

		this.setTitle("Modify Password");
		this.add(ModPwdPanel());
		this.setSize(260, 180);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
	}
	
	public JPanel ModPwdPanel(){
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		infoLabel = new JLabel("Old password isn't correct!");
		infoLabel.setForeground(Color.RED);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVisible(false);
		topPanel.add(infoLabel);
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new GridLayout(3,2));
		
		oldPwd = new JPasswordField(10);
		newPwd = new JPasswordField(10);
		confirmPwd = new JPasswordField(10);
		
		JLabel oldlabel = new JLabel("Old Password: ");
		JLabel newlabel = new JLabel("New Password: ");
		JLabel confirmlabel = new JLabel("Confirm Password: ");
		
		JPanel oldLabelPanel = new JPanel();
		oldLabelPanel.add(oldlabel);
		framePanel.add(oldLabelPanel);
		
		JPanel oldFieldPanel =  new JPanel();
		oldFieldPanel.add(oldPwd);
		framePanel.add(oldFieldPanel);
		
		JPanel newLabelPanel = new JPanel();
		newLabelPanel.add(newlabel);
		framePanel.add(newLabelPanel);
		
		JPanel newFieldPanel =  new JPanel();
		newFieldPanel.add(newPwd);
		framePanel.add(newFieldPanel);
		
		JPanel conLabelPanel = new JPanel();
		conLabelPanel.add(confirmlabel);
		framePanel.add(conLabelPanel);
		
		JPanel conFieldPanel =  new JPanel();
		conFieldPanel.add(confirmPwd);
		framePanel.add(conFieldPanel);
		
		panel.add(framePanel, BorderLayout.CENTER);
		
		JPanel buttomPanel = new JPanel();
        buttomPanel.add(createButton("Update"));
       // buttomPanel.add(createButton("Reset"));
        panel.add(buttomPanel, BorderLayout.SOUTH);
		return panel;
	}
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
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

	public JPasswordField getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(JPasswordField oldPwd) {
		this.oldPwd = oldPwd;
	}

	public JPasswordField getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(JPasswordField newPwd) {
		this.newPwd = newPwd;
	}

	public JPasswordField getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(JPasswordField confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
}
