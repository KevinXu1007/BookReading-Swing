package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import edu.tamuc.bean.BookInfo;
import edu.tamuc.dao.BookDao;
import edu.tamuc.dao.BookDaoImpl;
import edu.tamuc.util.FtpUtil;
import edu.tamuc.view.dialog.BookDetailDialog;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.AddBookPanel;
import edu.tamuc.view.panel.FindBookPanel;

public class AddBookAction implements ActionListener {
	
	private AddBookPanel addBookPanel;
	
	public AddBookAction(AddBookPanel addBookPanel){
		this.addBookPanel = addBookPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("Submit")) {
			if (addBookPanel.checkInputText()) {
				BookInfo book = addBookPanel.getTextValues();
				// System.out.println("=====" + book.getCategoryId());
				BookDao dao = new BookDaoImpl();
			    dao.addBookInfo(book);

				String fileDir = addBookPanel.getFileDir();
				// System.out.println(fileDir);

				FtpUtil ftp = new FtpUtil();
				ftp.fileUpload(fileDir, book.getFileName());
				
				JOptionPane.showMessageDialog(null, "Add Book Successfully", "Confirm",
    					JOptionPane.YES_OPTION);

				Vector data = dao.findBookList();
				
				FindBookPanel bookPanel = new FindBookPanel();
				bookPanel.setDataModel(data);
				MainFrame.getInstance().getCenter().add(bookPanel,"Find All Book");
				MainFrame.getInstance().getCenterCard().show(MainFrame.getInstance().getCenter(), "Find All Book");
			}
		} else if (str.equals("Update")) {
			if (addBookPanel.checkInputText()) {
				BookInfo book = addBookPanel.getTextValues();
				BookDao dao = new BookDaoImpl();
				int bookid = addBookPanel.getBookId();
				//System.out.println("=====" + bookid);
				dao.updateBookInfo(bookid, book);
				
				String fileDir = addBookPanel.getFileDir();
				if(!fileDir.equals("")){
					FtpUtil ftp = new FtpUtil();
					ftp.fileUpload(fileDir, book.getFileName());
				}
								
				
				JOptionPane.showMessageDialog(null, "Update Book Successfully", "Confirm",
    					JOptionPane.YES_OPTION);

				Vector data = dao.findBookList();
				
				FindBookPanel bookPanel = new FindBookPanel();
				bookPanel.setDataModel(data);
				MainFrame.getInstance().getCenter().add(bookPanel,"Find All Book");
				MainFrame.getInstance().getCenterCard().show(MainFrame.getInstance().getCenter(), "Find All Book");
				
			}
		}

	}
	

}
