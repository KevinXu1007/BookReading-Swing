package edu.tamuc.action;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.tamuc.bean.BookCategory;
import edu.tamuc.bean.UserInfo;
import edu.tamuc.dao.BookDao;
import edu.tamuc.dao.BookDaoImpl;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.FindBookPanel;

public class JTreeAction implements TreeSelectionListener{
	private MainFrame mainFrame;

	public JTreeAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	 @Override
     public void valueChanged(TreeSelectionEvent e) {
		JTree tree = mainFrame.getCategoryTree();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                .getLastSelectedPathComponent();

		BookCategory category = (BookCategory)node.getUserObject();
        //System.out.println(category.getId());
        
        BookDao dao = new BookDaoImpl();
		Vector data = dao.findBookListByCategory(category.getId());
		
		FindBookPanel bookPanel = new FindBookPanel();
		UserInfo user = mainFrame.getUser();
		if(user == null){
			bookPanel.getViewDetail().setEnabled(true);
			bookPanel.getViewBook().setEnabled(false);
			bookPanel.getDownloadBook().setEnabled(false);
			bookPanel.getModBook().setEnabled(false);
			bookPanel.getDelBook().setEnabled(false);
		}else if(user.getIsAdmin().equals("1")){
			bookPanel.getViewDetail().setEnabled(true);
			bookPanel.getViewBook().setEnabled(true);
			bookPanel.getDownloadBook().setEnabled(true);
			bookPanel.getModBook().setEnabled(true);
			bookPanel.getDelBook().setEnabled(true);
		}else if(user.getIsAdmin().equals("0")){
			bookPanel.getViewDetail().setEnabled(true);
			bookPanel.getViewBook().setEnabled(true);
			bookPanel.getDownloadBook().setEnabled(true);
			bookPanel.getModBook().setEnabled(false);
			bookPanel.getDelBook().setEnabled(false);
		}
		
		bookPanel.setDataModel(data);
		mainFrame.getCenter().add(bookPanel,"Find Book By Category");
		mainFrame.getCenterCard().show(mainFrame.getCenter(), "Find Book By Category");
		
     }

}
