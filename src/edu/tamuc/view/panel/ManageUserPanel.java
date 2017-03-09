package edu.tamuc.view.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import edu.tamuc.dao.UserDao;
import edu.tamuc.dao.UserDaoImpl;

public class ManageUserPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static ManageUserPanel instance;
	private JScrollPane scroll;
	private Vector title = new Vector();
	private DefaultTableModel model;
	private JTable table;
	private static JPopupMenu popupMenu;
	private JMenuItem resetItem=new JMenuItem("Reset Password");
	private JMenuItem setAdmin=new JMenuItem("Set As Admin");
	private JMenuItem cancelAdmin=new JMenuItem("Cancel As Admin");
	private JMenuItem deleteItem=new JMenuItem("Delete");
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public ManageUserPanel() {
        popupMenu=new JPopupMenu();	    
		popupMenu.add(resetItem);
	    popupMenu.add(setAdmin);
	    popupMenu.add(cancelAdmin);
	    popupMenu.add(deleteItem);
	    
	    resetItem.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
            	int show = JOptionPane.showConfirmDialog(null,
						"Are you sure to reset the password for this user?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (show == JOptionPane.YES_OPTION) {
					int row = getTable().getSelectedRow();
					String username = getTable().getValueAt(row, 0).toString();
					
					UserDao dao = new UserDaoImpl();
					dao.resetPasswordByName(username);
				}
            }  
        }); 
	    
	    setAdmin.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
            	int show = JOptionPane.showConfirmDialog(null,
						"Are you sure to set this user as admin?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (show == JOptionPane.YES_OPTION) {
					int row = getTable().getSelectedRow();
					String username = getTable().getValueAt(row, 0).toString();
					
					UserDao dao = new UserDaoImpl();
					dao.setAdminByName(username, "1");
					
					Vector data = dao.findUserList();
					setDataModel(data);
				}
            }  
        }); 
	    
	    cancelAdmin.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
            	int show = JOptionPane.showConfirmDialog(null,
						"Are you sure to cancel this user as admin?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (show == JOptionPane.YES_OPTION) {
					int row = getTable().getSelectedRow();
					String username = getTable().getValueAt(row, 0).toString();
					
					UserDao dao = new UserDaoImpl();
					dao.setAdminByName(username, "0");
					
					Vector data = dao.findUserList();
					setDataModel(data);
				}
            }  
        }); 
	    
	    deleteItem.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
            	int show = JOptionPane.showConfirmDialog(null,
						"Are you sure to delete this user?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (show == JOptionPane.YES_OPTION) {
					int row = getTable().getSelectedRow();
					String username = getTable().getValueAt(row, 0).toString();
					
					UserDao dao = new UserDaoImpl();
					dao.delUserByName(username);
					Vector data = dao.findUserList();
					setDataModel(data);
				}
            }  
        }); 
	    
		init();

	}
	
	public static ManageUserPanel getInstance() {
		if (instance == null) {
			instance = new ManageUserPanel();
		}
		return instance;
	}
	
	public void init(){
		this.setLayout(new BorderLayout());
		this.add(addJTableScrollPanel(), BorderLayout.CENTER);
		UserDao dao = new UserDaoImpl();
		Vector data = dao.findUserList();
		setDataModel(data);
	}
	
	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JScrollPane addJTableScrollPanel() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setViewportView(createJTable());
			hideTableColumn(table,0);			

			scroll.setBorder(BorderFactory.createTitledBorder("User List"));
		}
		return scroll;

	}
	
	public JTable createJTable() {
		title.add("id");
		title.add("User Name");
		title.add("Email");
		title.add("Is Admin(Y/N)");
		
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
	    //table.updateUI();
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
	
	private static MouseInputListener getMouseInputListener(final JTable jTable){
	       return new MouseInputListener() {
	           public void mouseClicked(MouseEvent e) {
	        	   
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

}
