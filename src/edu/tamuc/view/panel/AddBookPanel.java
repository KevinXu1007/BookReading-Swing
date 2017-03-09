package edu.tamuc.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.tamuc.action.AddBookAction;
import edu.tamuc.bean.BookCategory;
import edu.tamuc.bean.BookInfo;
import edu.tamuc.dao.BookDao;
import edu.tamuc.dao.BookDaoImpl;
import edu.tamuc.dao.CategoryDao;
import edu.tamuc.dao.CategoryDaoImpl;
import edu.tamuc.util.CategoryTree;
import edu.tamuc.util.CheckInputText;

public class AddBookPanel extends JPanel {

	private int bookId;
	private JPanel addInfoAndButtonPanel;
	private JPanel addInfoPanel;
	private JPanel addAreaInfoPanel;
	private JPanel addButtonPanel;
	
	private JLabel categoryLabel;
	private JLabel booknameLabel;
	private JLabel ISBNLabel;
	private JLabel authorLabel;
	private JLabel pagesLabel;
	private JLabel publisherLabel;
	private JLabel languageLabel;
	private JLabel dateLabel;
	private JLabel descLabel;
	private JLabel contentLabel;
	private JLabel fileLabel;
	private JLabel fileDirLabel;
	
	private JComboBox categoryTxt;
	private JTextField booknameTxt;
	private JTextField ISBNTxt;
	private JTextField authorTxt;
	private JTextField pagesTxt;
	private JTextField publisherTxt;
	private JTextField languageTxt;
	private JTextField dateTxt;
	private JTextArea descTxt;
	private JTextArea contentTxt;
	private JButton fileButton;
	private JScrollPane scrollDescPanel;
	private JScrollPane scrollConPanel;
	
	private JButton addButton;
	private JButton updateButton;
	
	private File prevDirChoice;
	private JSplitPane split;
	private FileFilter pdfFilter = new FileFilter() {

	       public boolean accept(File f) {
	           return f.isDirectory() || f.getName().endsWith(".pdf");
	       }

	       public String getDescription() {
	           return "Choose a PDF file";
	       }
	   };
	
	private AddBookAction action = new AddBookAction(this);

	/**
	 * Create the panel.
	 */
	public AddBookPanel() {
		initJLabel();
		initJTextField();
		this.setLayout(new BorderLayout());
		this.add(addInfoAndButtonPanel(), BorderLayout.NORTH);
		

	}
	
	public void initJLabel() {
		categoryLabel = new JLabel("Category: ");
		booknameLabel = new JLabel("Book Name: ");
		ISBNLabel = new JLabel("ISBN: ");
		authorLabel = new JLabel("Author: ");
		pagesLabel = new JLabel("Pages: ");
		publisherLabel = new JLabel("Publisher: ");
		languageLabel = new JLabel("Language: ");
		dateLabel = new JLabel("Publish Date(YYYY-MM-DD): ");
		descLabel = new JLabel("Description: ");
		contentLabel = new JLabel("Content: ");
		fileLabel = new JLabel("Select File: ");
		fileDirLabel = new JLabel("");
	}
	
	public void initJTextField(){
		booknameTxt = new JTextField(20);
		ISBNTxt = new JTextField(20);
		authorTxt = new JTextField(20);
		pagesTxt = new JTextField(20);
		publisherTxt = new JTextField(20);
		languageTxt = new JTextField(20);
		dateTxt = new JTextField(20);
		descTxt = new JTextArea(5, 10); 
		scrollDescPanel = new JScrollPane(descTxt); 
		contentTxt = new JTextArea(5, 10); 
		scrollConPanel = new JScrollPane(contentTxt); 
		fileButton = new JButton("select file");
		
		class SelectFileListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				doOpen();
			}
		}
		
		 ActionListener listener = new SelectFileListener();
		 fileButton.addActionListener(listener);

	}
	
	public void doOpen() {
	       try {
	           JFileChooser fc = new JFileChooser();
	           fc.setCurrentDirectory(prevDirChoice);
	           fc.setFileFilter(pdfFilter);
	           fc.setMultiSelectionEnabled(false);
	           int returnVal = fc.showOpenDialog(this);
	           if (returnVal == JFileChooser.APPROVE_OPTION) {
	               prevDirChoice = fc.getSelectedFile();
	               fileDirLabel.setText(fc.getSelectedFile().toString());	              
	           }
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(split,
	                   "Opening files from your local " +
	                   "disk is not available\nfrom the " +
	                   "Java Web Start version of this " +
	                   "program.\n",
	                   "Error opening directory",
	                   JOptionPane.ERROR_MESSAGE);
	           e.printStackTrace();
	       }
	   }
	
	public JPanel addInfoAndButtonPanel() {
		if (addInfoAndButtonPanel == null) {
			addInfoAndButtonPanel = new JPanel();
			addInfoAndButtonPanel.setLayout(new BorderLayout());
			addInfoAndButtonPanel.add(addInfoPanel(), BorderLayout.WEST);
			addInfoAndButtonPanel.add(addAreaInfoPanel(), BorderLayout.CENTER);
			addInfoAndButtonPanel.add(addButtonPanel(), BorderLayout.SOUTH);
		}
		return addInfoAndButtonPanel;

	}
	
	
	public JPanel addInfoPanel() {
		if (addInfoPanel == null) {
			addInfoPanel = new JPanel();
			addInfoPanel.setLayout(new GridLayout(10,2));
			
			addInfoPanel.add(categoryLabel);
			addInfoPanel.add(createBox());
			
			addInfoPanel.add(booknameLabel);
			addInfoPanel.add(booknameTxt);
			
			addInfoPanel.add(ISBNLabel);
			addInfoPanel.add(ISBNTxt);
			
			addInfoPanel.add(authorLabel);
			addInfoPanel.add(authorTxt);
			
			addInfoPanel.add(pagesLabel);
			addInfoPanel.add(pagesTxt);
			
			addInfoPanel.add(publisherLabel);
			addInfoPanel.add(publisherTxt);
			
			addInfoPanel.add(languageLabel);
			addInfoPanel.add(languageTxt);
			
			addInfoPanel.add(dateLabel);
			addInfoPanel.add(dateTxt);
			
			addInfoPanel.add(fileLabel);
			addInfoPanel.add(fileButton);
			
			addInfoPanel.add(new JLabel(" "));
			addInfoPanel.add(fileDirLabel);
			
			addInfoPanel.setBorder(BorderFactory.createTitledBorder("Book Detail Information"));

		}
		return addInfoPanel;
	}
	
	public JPanel addAreaInfoPanel() {
		if (addAreaInfoPanel == null) {
			addAreaInfoPanel = new JPanel();
			addAreaInfoPanel.setLayout(new GridLayout(4,1));
			addAreaInfoPanel.add(descLabel);
			addAreaInfoPanel.add(scrollDescPanel);
			
			addAreaInfoPanel.add(contentLabel);
			addAreaInfoPanel.add(scrollConPanel);
			
			addAreaInfoPanel.setBorder(BorderFactory.createTitledBorder("Add Book Information"));
		}
		return addAreaInfoPanel;
	}
	
	public JPanel addButtonPanel() {
		if (addButtonPanel == null) {
			addButtonPanel = new JPanel();
			addButton = createButton("Submit");
			addButtonPanel.add(addButton);
			updateButton = createButton("Update");
			addButtonPanel.add(updateButton);
			updateButton.setVisible(false);
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
		if (categoryTxt == null) {
			CategoryTree category = new CategoryTree();
			Vector categoryVector = category.getCategoryList();
			categoryTxt = new JComboBox(categoryVector);
		}
		return categoryTxt;
	}
	
	
	public boolean checkInputText() {
		CheckInputText check = new CheckInputText();
		BookDao dao = new BookDaoImpl();
				
		if (check.checkInputIsNull(booknameTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please input book name", "alert",
					JOptionPane.YES_OPTION);
			booknameTxt.requestFocus();
			return false;
		}		
		
		
		if (check.checkInputIsNull(ISBNTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please input ISBN", "alert",
					JOptionPane.YES_OPTION);
			ISBNTxt.requestFocus();
			return false;
		}
		
		if (bookId == 0 && check.checkInputIsNull(fileDirLabel.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Please select .pdf file for the book", "alert",
					JOptionPane.YES_OPTION);
			//ISBNTxt.requestFocus();
			return false;
		}
		
		if (!pagesTxt.getText().trim().equals("") && !check.checkIsContainNumber(pagesTxt.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Please input currect pages number", "alert",
					JOptionPane.YES_OPTION);
			pagesTxt.requestFocus();
			return false;
		}
		
		if (!check.checkDateFormatIsLegal(dateTxt.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Please input date format", "alert",
					JOptionPane.YES_OPTION);
			dateTxt.requestFocus();
			return false;
		}
		
		if(bookId == 0 && dao.findBookByISBN(ISBNTxt.getText().trim()) != null){
			JOptionPane.showMessageDialog(null, "This Book ISBN exists", "alert",
					JOptionPane.YES_OPTION);
			pagesTxt.requestFocus();
			return false;
		}
		
		
		return true;
	}
	
	public String getFileDir(){
		return fileDirLabel.getText();
	}
	
	public BookInfo getTextValues(){
		int categoryId = ((BookCategory)categoryTxt.getSelectedItem()).getId();
		String bookName = booknameTxt.getText().trim();
		String ISBN = ISBNTxt.getText().trim();
		String author = authorTxt.getText().trim();
		int pages = 0;
		if(!pagesTxt.getText().trim().equals("")){
			pages = Integer.parseInt(pagesTxt.getText().trim());
		}
		
		String publisher = publisherTxt.getText().trim();
		String language = languageTxt.getText().trim();
		String publishDate = dateTxt.getText().trim();
		String desc = descTxt.getText().trim();
		String content = contentTxt.getText().trim();
		String fileName = ISBN + ".pdf";
		
		BookInfo book = new BookInfo(categoryId, bookName, ISBN, author, pages, publisher, language,
				publishDate, desc, content, fileName);
		
		return book;
	}

	public JComboBox getCategoryTxt() {
		return categoryTxt;
	}

	public void setCategoryTxt(JComboBox categoryTxt) {
		this.categoryTxt = categoryTxt;
	}

	public JTextField getBooknameTxt() {
		return booknameTxt;
	}

	public void setBooknameTxt(JTextField booknameTxt) {
		this.booknameTxt = booknameTxt;
	}

	public JTextField getISBNTxt() {
		return ISBNTxt;
	}

	public void setISBNTxt(JTextField iSBNTxt) {
		ISBNTxt = iSBNTxt;
	}

	public JTextField getAuthorTxt() {
		return authorTxt;
	}

	public void setAuthorTxt(JTextField authorTxt) {
		this.authorTxt = authorTxt;
	}

	public JTextField getPagesTxt() {
		return pagesTxt;
	}

	public void setPagesTxt(JTextField pagesTxt) {
		this.pagesTxt = pagesTxt;
	}

	public JTextField getPublisherTxt() {
		return publisherTxt;
	}

	public void setPublisherTxt(JTextField publisherTxt) {
		this.publisherTxt = publisherTxt;
	}

	public JTextField getLanguageTxt() {
		return languageTxt;
	}

	public void setLanguageTxt(JTextField languageTxt) {
		this.languageTxt = languageTxt;
	}

	public JTextField getDateTxt() {
		return dateTxt;
	}

	public void setDateTxt(JTextField dateTxt) {
		this.dateTxt = dateTxt;
	}

	public JTextArea getDescTxt() {
		return descTxt;
	}

	public void setDescTxt(JTextArea descTxt) {
		this.descTxt = descTxt;
	}

	public JTextArea getContentTxt() {
		return contentTxt;
	}

	public void setContentTxt(JTextArea contentTxt) {
		this.contentTxt = contentTxt;
	}

	public JButton getFileButton() {
		return fileButton;
	}

	public void setFileButton(JButton fileButton) {
		this.fileButton = fileButton;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}
	
	

}
