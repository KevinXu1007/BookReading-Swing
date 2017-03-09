package edu.tamuc.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.tamuc.action.UserAction;
import edu.tamuc.bean.UserInfo;
import edu.tamuc.dao.UserDao;
import edu.tamuc.dao.UserDaoImpl;
import edu.tamuc.util.CheckInputText;


public class AddUserPanel extends JPanel {
	
	private JPanel addInfoAndButtonPanel;
	private JPanel addInfoPanel;
	private JPanel addButtonPanel;
	
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JLabel adminLabel;
	
	private JTextField username;
	private JTextField email;
	private JComboBox isadmin;
	
	private UserAction action = new UserAction(this);

	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		
		this.setLayout(new BorderLayout());
		this.add(addInfoAndButtonPanel(), BorderLayout.NORTH);
		

	}
	
	public JPanel addInfoAndButtonPanel() {
		if (addInfoAndButtonPanel == null) {
			addInfoAndButtonPanel = new JPanel();
			addInfoAndButtonPanel.setLayout(new BorderLayout());
			addInfoAndButtonPanel.add(addInfoPanel(), BorderLayout.WEST);
			addInfoAndButtonPanel.add(addButtonPanel(), BorderLayout.SOUTH);
		}
		return addInfoAndButtonPanel;

	}
	
	
	public JPanel addInfoPanel() {
		if (addInfoPanel == null) {
			addInfoPanel = new JPanel();
			addInfoPanel.setLayout(new GridLayout(3,2));
			usernameLabel = new JLabel("User Name:");
			usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			addInfoPanel.add(usernameLabel);
			username = new JTextField(20);
			addInfoPanel.add(username);
			emailLabel = new JLabel("Email:");
			emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			addInfoPanel.add(emailLabel);
			email = new JTextField(20);
			addInfoPanel.add(email);
			
			adminLabel = new JLabel("Is Admin:");
			adminLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			addInfoPanel.add(adminLabel);
			addInfoPanel.add(createBox());
			
			
			addInfoPanel.setBorder(BorderFactory.createTitledBorder("Add User Information"));

		}
		return addInfoPanel;
	}
	
	public JPanel addButtonPanel() {
		if (addButtonPanel == null) {
			addButtonPanel = new JPanel();
			addButtonPanel.add(createButton("Submit"));
			addButtonPanel.setBorder(BorderFactory.createTitledBorder("Performance Action"));
		}
		return addButtonPanel;
	}
	
	public JButton createButton(String name) {
		JButton btnSubmit = new JButton(name);
		btnSubmit.addActionListener(action);
		return btnSubmit;
	}
	
	public JComboBox createBox() {
		if (isadmin == null) {
			Vector v = new Vector();
			v.add("Yes");
			v.add("No");
			
			isadmin = new JComboBox(v);

		}
		return isadmin;
	}
	
	public boolean checkInputText() {
		CheckInputText check = new CheckInputText();
		UserDao dao = new UserDaoImpl();
				
		if (check.checkInputIsNull(username.getText())) {
			JOptionPane.showMessageDialog(null, "Please input user name", "alert",
					JOptionPane.YES_OPTION);
			username.requestFocus();
			return false;
		}	
		
		if(dao.findUserByName(username.getText().trim()) != null){
			JOptionPane.showMessageDialog(null, "This user name exists", "alert",
					JOptionPane.YES_OPTION);
			username.requestFocus();
			return false;
		}
		
		if (check.checkInputIsNull(email.getText())) {
			JOptionPane.showMessageDialog(null, "Please input user email", "alert",
					JOptionPane.YES_OPTION);
			email.requestFocus();
			return false;
		}
		
		if (!check.checkEmailFormatIsLegal(email.getText())) {
			JOptionPane.showMessageDialog(null, "Please input correct email format", "alert",
					JOptionPane.YES_OPTION);
			email.requestFocus();
			return false;
		}
		
		return true;
	}
	
	public UserInfo getTextValues(){
		String name = username.getText().trim();
		String mail = email.getText().trim();
		String admin = isadmin.getSelectedItem().toString();
		if(admin.equals("Yes")){
			admin = "1";
		}else{
			admin = "0";
		}
		UserInfo user = new UserInfo(name, mail, admin);
		
		return user;
	}

}
