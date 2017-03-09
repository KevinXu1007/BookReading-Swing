package edu.tamuc.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import edu.tamuc.action.FindBookAction;
import edu.tamuc.bean.BookInfo;
import edu.tamuc.bean.UserInfo;
import edu.tamuc.view.dialog.ViewBookDialog;
import edu.tamuc.view.frame.MainFrame;

import javax.swing.SwingConstants;

public class FindBookPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel addInfoAndButtonPanel;
	private JPanel addInfoPanel;
	private JPanel addButtonPanel;
	private JScrollPane scroll;
	private JTextField bookNameTxt;
	private JTextField ISBNTxt;
	
	private Vector title = new Vector();
	private DefaultTableModel model;
	private JTable table;
	private static JPopupMenu popupMenu;
	private JMenuItem viewDetail;
	private JMenuItem viewBook;
	private JMenuItem downloadBook;
	private JMenuItem modBook;
	private JMenuItem delBook;
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	private FindBookAction action = new FindBookAction(this);
	
	public FindBookPanel() {
		popupMenu=new JPopupMenu();
		
		viewDetail = new JMenuItem("View Detail");
		viewBook = new JMenuItem("View Book");
		downloadBook = new JMenuItem("Download Book");
		modBook = new JMenuItem("Modify Book");
		delBook = new JMenuItem("Delete Book");
		
		popupMenu.add(viewDetail);
		popupMenu.add(viewBook);
		popupMenu.add(downloadBook);
		popupMenu.add(modBook);
		popupMenu.add(delBook);
		
		viewDetail.addActionListener(action);
		viewBook.addActionListener(action);
		downloadBook.addActionListener(action);
		modBook.addActionListener(action);
		delBook.addActionListener(action);
	    
		/*addJMenuItem("View Detail");
	    //MainFrame.getInstance();
		addJMenuItem("View Book");
	    addJMenuItem("Download Book");
	    
	    addJMenuItem("Modify Book");
	    addJMenuItem("Delete Book");*/
	   
		this.setLayout(new BorderLayout());
		this.add(addInfoAndButtonPanel(), BorderLayout.NORTH);
		this.add(addJTableScrollPanel());
	}
	
	/*private void addJMenuItem(String name) {
		JMenuItem item = new JMenuItem(name);
		item.addActionListener(action);
		popupMenu.add(item);
	}*/
	
	public JPanel addInfoAndButtonPanel() {
		if (addInfoAndButtonPanel == null) {
			addInfoAndButtonPanel = new JPanel();
			addInfoAndButtonPanel.setLayout(new BorderLayout());
			addInfoAndButtonPanel.add(addInfoPanel(), BorderLayout.WEST);
			addInfoAndButtonPanel.add(addButtonPanel(), BorderLayout.SOUTH);
		}
		return addInfoAndButtonPanel;

	}
	
	public JPanel addInfoPanel() {
		if (addInfoPanel == null) {
			addInfoPanel = new JPanel();
			addInfoPanel.setLayout(new GridLayout(1,4));
			JLabel label = new JLabel("ISBN:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			addInfoPanel.add(label);
			ISBNTxt = new JTextField(10);
			addInfoPanel.add(ISBNTxt);
			JLabel label_1 = new JLabel("Book Name:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			addInfoPanel.add(label_1);
			bookNameTxt = new JTextField(10);
			addInfoPanel.add(bookNameTxt);
			addInfoPanel.setBorder(BorderFactory.createTitledBorder("Query Conditions"));

		}
		return addInfoPanel;
	}
	
	public JPanel addButtonPanel() {
		if (addButtonPanel == null) {
			addButtonPanel = new JPanel();
			addButtonPanel.add(createButton("Query"));
			//addButtonPanel.setBorder(BorderFactory.createTitledBorder("Performance Way"));
		}
		return addButtonPanel;
	}
	
	public JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setVerticalAlignment(SwingConstants.BOTTOM);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.addActionListener(action);
		return button;
	}
	
	public JScrollPane addJTableScrollPanel() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setViewportView(createJTable());
			hideTableColumn(table,0);			

			scroll.setBorder(BorderFactory.createTitledBorder("Book List"));
		}
		return scroll;

	}
	
	public JTable createJTable() {
		title.add("id");
		title.add("ISBN");
		title.add("Book Name");
		title.add("Author");
		title.add("Year");
		//title.add("Action");
		
		if (table == null) {
			model = new DefaultTableModel(new Vector(), title) {
                private static final long serialVersionUID = 1L;
                @Override
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
            };
			table = new JTable(model);
		}
		table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
		MouseInputListener mouseInputListener = getMouseInputListener(table);
		table.addMouseListener(mouseInputListener);
	    table.addMouseMotionListener(mouseInputListener);
	    return table;
	}
	
	private void hideTableColumn(JTable table, int column){  
	    TableColumnModel tcm = table.getColumnModel();  
	    TableColumn tc = tcm.getColumn(column);
	    tcm.removeColumn(tc);         
	}  
	
	public void setDataModel(Vector data) {
		model.setDataVector(data, title);
		hideTableColumn(table,0);
	}
	
	public BookInfo getTextValues(){
		String bookName = bookNameTxt.getText().trim();
		String ISBN = ISBNTxt.getText().trim();
		
		BookInfo book = new BookInfo();
		book.setBookName(bookName);
		book.setISBN(ISBN);
		
		return book;
	}
	
	private static MouseInputListener getMouseInputListener(final JTable jTable){
	       return new MouseInputListener() {
	           public void mouseClicked(MouseEvent e) {
	        	   if (e.getClickCount() == 2) {
	        		   //ViewBookDialog.getInstance().setVisible(true);
                   }
	           }
	           public void mousePressed(MouseEvent e) {
	              processEvent(e);
	           }
	           public void mouseReleased(MouseEvent e) {
	              processEvent(e);
	              if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0
	                     && !e.isControlDown() && !e.isShiftDown()) {
	                popupMenu.show(e.getComponent(), e.getX(), e.getY());
	              }
	           }
	           public void mouseEntered(MouseEvent e) {
	              processEvent(e);
	           }
	 
	           public void mouseExited(MouseEvent e) {
	              processEvent(e);
	           }
	 
	           public void mouseDragged(MouseEvent e) {
	              processEvent(e);
	           }
	 
	           public void mouseMoved(MouseEvent e) {
	              processEvent(e);
	           }
	           private void processEvent(MouseEvent e) {
	              if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
	                  int modifiers = e.getModifiers();
	                  modifiers -= MouseEvent.BUTTON3_MASK;
	                  modifiers |= MouseEvent.BUTTON1_MASK;
	                  MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(),
	                         e.getWhen(), modifiers, e.getX(), e.getY(), e
	                                .getClickCount(), false);
	                  jTable.dispatchEvent(ne);
	              }
	           }
	       };
	    }

	public JMenuItem getViewDetail() {
		return viewDetail;
	}

	public void setViewDetail(JMenuItem viewDetail) {
		this.viewDetail = viewDetail;
	}

	public JMenuItem getViewBook() {
		return viewBook;
	}

	public void setViewBook(JMenuItem viewBook) {
		this.viewBook = viewBook;
	}

	public JMenuItem getDownloadBook() {
		return downloadBook;
	}

	public void setDownloadBook(JMenuItem downloadBook) {
		this.downloadBook = downloadBook;
	}

	public JMenuItem getModBook() {
		return modBook;
	}

	public void setModBook(JMenuItem modBook) {
		this.modBook = modBook;
	}

	public JMenuItem getDelBook() {
		return delBook;
	}

	public void setDelBook(JMenuItem delBook) {
		this.delBook = delBook;
	}
	
	
	

}
