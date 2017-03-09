package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import edu.tamuc.bean.UserInfo;
import edu.tamuc.dao.UserDao;
import edu.tamuc.dao.UserDaoImpl;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.AddUserPanel;
import edu.tamuc.view.panel.ManageUserPanel;

public class UserAction implements ActionListener{
	
	private AddUserPanel addUserPanel;
	
	public UserAction(AddUserPanel _addUserPanel){
		this.addUserPanel = _addUserPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();	
	if(str.equals("Submit")){
		if (addUserPanel.checkInputText()) {
			UserInfo user = addUserPanel.getTextValues();
			UserDao dao = new UserDaoImpl();
			dao.addUserInfo(user);
			MainFrame.getInstance().addCenterCardPanel(MainFrame.getInstance().getCenter());
			MainFrame.getInstance().getCenterCard().show(MainFrame.getInstance().getCenter(), "Users");
		}	
	}
	}

}
