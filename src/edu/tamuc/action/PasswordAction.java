package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import edu.tamuc.bean.UserInfo;
import edu.tamuc.dao.UserDao;
import edu.tamuc.dao.UserDaoImpl;
import edu.tamuc.view.dialog.LoginDialog;
import edu.tamuc.view.dialog.ModPwdDialog;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.LoginPanel;

public class PasswordAction  implements ActionListener{
	private ModPwdDialog pwdDialog;
	
	public PasswordAction(ModPwdDialog pwdDialog){
		this.pwdDialog = pwdDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		UserDao dao = new UserDaoImpl();
		String usr = pwdDialog.getUser().getUserName();
		String old = dao.findUserByName(usr).getPassword();
		String oldInput = pwdDialog.getOldPwd().getText();
		String newPwd = pwdDialog.getNewPwd().getText();
		String confirmPwd = pwdDialog.getConfirmPwd().getText();
		if (str.equals("Update")) {
			System.out.println(usr);
			if(!old.equals(oldInput)){
				pwdDialog.getInfoLabel().setText("Old password isn't correct!");
				pwdDialog.getInfoLabel().setVisible(true);
			}else if(!newPwd.equals(confirmPwd)){
				pwdDialog.getInfoLabel().setText("New password and confirm password are not same");
				pwdDialog.getInfoLabel().setVisible(true);
			}else{
				
				dao.updatePwdByName(usr, newPwd);
				pwdDialog.getInfoLabel().setVisible(false);
				pwdDialog.getOldPwd().setText("");
				pwdDialog.getNewPwd().setText("");
				pwdDialog.getConfirmPwd().setText("");				
				pwdDialog.setVisible(false);
				
			}
			
						
			
		}
		
	}
	

}
