package edu.tamuc.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeModel;

import edu.tamuc.bean.BookCategory;
import edu.tamuc.bean.BookInfo;
import edu.tamuc.dao.CategoryDao;
import edu.tamuc.dao.CategoryDaoImpl;
import edu.tamuc.view.panel.AddBookPanel;


public class BookDetailDialog extends JDialog {

	private static BookDetailDialog instance;
	private AddBookPanel bookPanel= new AddBookPanel();
	private BookInfo book;
	private String opt;
	
	public BookDetailDialog(BookInfo book, String opt){
		this.book = book;
		this.opt = opt;
		init();
	}
	
	public static BookDetailDialog getInstance(BookInfo book, String opt){
		instance = new BookDetailDialog(book, opt);		
		return instance;
	}
	
	public void init() {
		this.setTitle("Book Detail");
		initPanel();
		this.add(bookPanel);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setModal(true);
	}
	
	public void initPanel() {
		bookPanel.setBookId(book.getId());
		bookPanel.getBooknameTxt().setText(book.getBookName());
		bookPanel.getISBNTxt().setText(book.getISBN());
		bookPanel.getAuthorTxt().setText(book.getAuthor());
		bookPanel.getPagesTxt().setText(String.valueOf(book.getPages()));
		bookPanel.getPublisherTxt().setText(book.getPublisher());
		bookPanel.getLanguageTxt().setText(book.getLanguage());
		bookPanel.getDateTxt().setText(book.getPublishDate());
		
		bookPanel.getDescTxt().setText(book.getDesc());
		bookPanel.getContentTxt().setText(book.getContent());
		
		BookCategory cate = (BookCategory)getCategory(book.getCategoryId());
		
		
		selectCategory(cate);
		
		if (opt.equals("modify")) {
			bookPanel.getAddButton().setVisible(false);
			bookPanel.getUpdateButton().setVisible(true);
			bookPanel.getFileButton().setVisible(true);
			
		}else if(opt.equals("view")){
			bookPanel.getAddButton().setVisible(false);
			bookPanel.getUpdateButton().setVisible(false);
			bookPanel.getFileButton().setVisible(false);
			
			bookPanel.getBooknameTxt().setEditable(false);
			bookPanel.getISBNTxt().setEditable(false);
			bookPanel.getAuthorTxt().setEditable(false);
			bookPanel.getPagesTxt().setEditable(false);
			bookPanel.getPublisherTxt().setEditable(false);
			bookPanel.getLanguageTxt().setEditable(false);
			bookPanel.getDateTxt().setEditable(false);
			
			bookPanel.getDescTxt().setEditable(false);
			bookPanel.getContentTxt().setEditable(false);
			bookPanel.getCategoryTxt().setEnabled(false);
		}
		
		
	}
	
	public BookCategory getCategory(int cid){
		CategoryDao dao = new CategoryDaoImpl();
		return dao.getCategoryById(cid);
		
	}
	
	public void selectCategory(BookCategory cate){
		int num = bookPanel.getCategoryTxt().getItemCount();
		BookCategory temp;
		for(int i=1; i<num; i++){
			temp = (BookCategory)(bookPanel.getCategoryTxt().getItemAt(i));
			if((temp.getCategoryName()).equals(cate.getCategoryName())){
				bookPanel.getCategoryTxt().setSelectedIndex(i);
				break;
			}
		}
		
	}

}
