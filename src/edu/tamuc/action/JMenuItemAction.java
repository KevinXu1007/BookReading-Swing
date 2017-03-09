package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.tamuc.view.dialog.LoginDialog;
import edu.tamuc.view.frame.MainFrame;

public class JMenuItemAction implements ActionListener  {
	private MainFrame mainFrame;

	public JMenuItemAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Login")){
			LoginDialog.getInstance().setVisible(true);
		}

	}
}
