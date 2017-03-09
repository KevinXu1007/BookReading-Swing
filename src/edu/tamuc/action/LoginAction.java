package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import edu.tamuc.bean.UserInfo;
import edu.tamuc.dao.UserDao;
import edu.tamuc.dao.UserDaoImpl;
import edu.tamuc.view.dialog.LoginDialog;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.LoginPanel;

public class LoginAction  implements ActionListener{
	private LoginPanel loginPanel;
	
	public LoginAction(LoginPanel _loginPanel){
		this.loginPanel = _loginPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		String usr = loginPanel.getUsername().getText();
		String pwd = loginPanel.getPwd().getText();
		if (str.equals("Sign In")) {
			UserDao dao = new UserDaoImpl();
			UserInfo user = dao.findUserInfo(usr, pwd);
			if(user!=null){
				//System.out.println("successfully login " + user.getIsAdmin());
				loginPanel.getInfoLabel().setVisible(false);
				loginPanel.getUsername().setText("");
				loginPanel.getPwd().setText("");
				LoginDialog.getInstance().setVisible(false);
				MainFrame.getInstance().setUser(user);
				MainFrame.getInstance().init();
				MainFrame.getInstance().getWestCard().show(MainFrame.getInstance().getWest(), "Category");
			}else{
				loginPanel.getInfoLabel().setVisible(true);
				loginPanel.getUsername().setText("");
				loginPanel.getPwd().setText("");
			}
			
		}else if(str.equals("Reset")){
			loginPanel.getUsername().setText("");
			loginPanel.getPwd().setText("");
			
		}
		
	}
	

}
