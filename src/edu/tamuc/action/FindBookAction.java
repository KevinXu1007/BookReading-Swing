package edu.tamuc.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.tamuc.bean.BookInfo;
import edu.tamuc.dao.BookDao;
import edu.tamuc.dao.BookDaoImpl;
import edu.tamuc.util.FtpUtil;
import edu.tamuc.view.dialog.BookDetailDialog;
import edu.tamuc.view.dialog.LoginDialog;
import edu.tamuc.view.dialog.ViewBookDialog;
import edu.tamuc.view.panel.FindBookPanel;


public class FindBookAction implements ActionListener {
	private FindBookPanel findBookPanel;

	public FindBookAction(FindBookPanel _findBookPanel) {
		this.findBookPanel = _findBookPanel;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();		
		
		if (str.equals("Query")) {
			BookInfo book = findBookPanel.getTextValues();
			BookDao dao = new BookDaoImpl();
			Vector data = dao.findBookListByCon(book.getBookName(),
					book.getISBN());

			findBookPanel.setDataModel(data);
			
			
		} else if (str.equals("View Book")) {
			int row = findBookPanel.getTable().getSelectedRow();
			String isbn = findBookPanel.getTable().getValueAt(row, 0)
					.toString();
			String fileName = isbn + ".pdf";
			FtpUtil ftp = new FtpUtil();
			ftp.fileDownload(fileName, fileName);
			
			ViewBookDialog.getInstance(isbn + ".pdf").setVisible(true);
			System.out.println(isbn);

		}else if (str.equals("View Detail")) {
			int row = findBookPanel.getTable().getSelectedRow();
			String isbn = findBookPanel.getTable().getValueAt(row, 0)
					.toString();
			BookDao dao = new BookDaoImpl();
			BookInfo book = dao.findBookByISBN(isbn);
			BookDetailDialog.getInstance(book, "view").setVisible(true);
		}else if (str.equals("Download Book")) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(1);
            int state = jfc.showOpenDialog(null);
            if (state == 1) {  
                return;  
            } else {  
            	int row = findBookPanel.getTable().getSelectedRow();
        		String isbn = findBookPanel.getTable().getValueAt(row, 0)
        				.toString();
        		String fileName = isbn + ".pdf";
        		FtpUtil ftp = new FtpUtil();
                File f = jfc.getSelectedFile();
                String clientFile = f.getAbsolutePath() + "\\" +fileName;
                ftp.fileDownload(fileName, clientFile);
                System.out.println(clientFile);
                JOptionPane.showMessageDialog(null, "Download Successfully", "Confirm",
    					JOptionPane.YES_OPTION);
            }  
			
		}else if (str.equals("Modify Book")) {
			int row = findBookPanel.getTable().getSelectedRow();
			String isbn = findBookPanel.getTable().getValueAt(row, 0)
					.toString();
			BookDao dao = new BookDaoImpl();
			BookInfo book = dao.findBookByISBN(isbn);
			BookDetailDialog.getInstance(book, "modify").setVisible(true);
		}else if (str.equals("Delete Book")) {
			int show = JOptionPane.showConfirmDialog(null,
					"Are you sure to delete this book?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (show == JOptionPane.YES_OPTION) {
				int row = findBookPanel.getTable().getSelectedRow();
				String isbn = findBookPanel.getTable().getValueAt(row, 0)
						.toString();
				BookDao dao = new BookDaoImpl();
				dao.delBookByISBN(isbn);
				Vector data = dao.findBookList();
				findBookPanel.setDataModel(data);
			}
		}

	}
}
