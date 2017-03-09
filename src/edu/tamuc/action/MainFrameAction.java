package edu.tamuc.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.tamuc.bean.UserInfo;
import edu.tamuc.view.dialog.CategoryDialog;
import edu.tamuc.view.dialog.LoginDialog;
import edu.tamuc.view.dialog.ModPwdDialog;
import edu.tamuc.view.frame.MainFrame;

public class MainFrameAction implements ActionListener {
	private MainFrame frame;

	public MainFrameAction(MainFrame frame) {
		this.frame = frame;

	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		JPanel center = frame.getCenter();
		JPanel west = frame.getWest();
		CardLayout centerCard = frame.getCenterCard();
		CardLayout westCard = frame.getWestCard();
		if (str.equals("Add Book")) {
			centerCard.show(center, "Add Book");				
			westCard.show(west, "Category");
		} else if (str.equals("Query Book")) {
			centerCard.show(center, "Query Book");			
			westCard.show(west, "Category");
		} else if (str.equals("Login")) {
			LoginDialog.getInstance().setVisible(true);
			
		}else if (str.equals("Logout")) {
			frame.setUser(null);
			frame.init();
			centerCard.show(center, "Query Book");			
			westCard.show(west, "Category");
		}else if (str.equals("Users")) {			
			centerCard.show(center, "Users");
			westCard.show(west, "UserSide");
		}else if (str.equals("Edit Category")) {			
			CategoryDialog.getInstance().setVisible(true);
		}else if (str.equals("Add User")) {			
			centerCard.show(center, "Add User");
		}else if (str.equals("Manage User")) {
			
			centerCard.show(center, "Users");
		}else if (str.equals("Modify Password")) {
			UserInfo user = frame.getUser();
			//System.out.println(user.getUserName());
			ModPwdDialog.getInstance().setUser(user);
			ModPwdDialog.getInstance().setVisible(true);
			
		}
		
	}

}
