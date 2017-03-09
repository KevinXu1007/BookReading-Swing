package edu.tamuc.view.frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import edu.tamuc.action.JTreeAction;
import edu.tamuc.action.MainFrameAction;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.tamuc.action.JMenuItemAction;
import edu.tamuc.bean.UserInfo;
import edu.tamuc.view.panel.AddBookPanel;
import edu.tamuc.view.panel.AddUserPanel;
import edu.tamuc.view.panel.CreateCategoryTree;
import edu.tamuc.view.panel.FindBookPanel;
import edu.tamuc.view.panel.ManageUserPanel;

public class MainFrame extends JFrame {

	private static MainFrame instance;
	private JToolBar tool;
	//private JMenuBar bar;
	//private JMenu systemJMenu;
	//private JMenu bookJMenu;
	private JPanel center = new JPanel();
	private CardLayout centerCard = new CardLayout();
	private JPanel west = new JPanel();
	private CardLayout westCard = new CardLayout();
	private JSplitPane split;
	private MainFrameAction action = new MainFrameAction(this);
	//private JMenuItemAction menuItemAction = new JMenuItemAction(this);
	private CreateCategoryTree categoryTree = new CreateCategoryTree();	
	private JTreeAction treeAction = new JTreeAction(this);
    private UserInfo user = null; 
	
	
	private MainFrame() {
		init();
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public void init() {
		this.setTitle("Online Book Reading");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(size.width, size.height - 20);
		this.setLocationRelativeTo(null);
		
		getContentPane().add(createTool(), BorderLayout.NORTH);
		setTool();
		
		//this.setJMenuBar(createJMenuBar());

		center.setLayout(centerCard);
		addCenterCardPanel(center);
		
		west.setLayout(westCard);
		addWestCardPanel(west);
		

		getContentPane().add(createSplit());
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		
		//createSystemTray();
		closeWindow(this);

		this.setVisible(true);
	}
	
	public JToolBar createTool() {
		if (tool == null) {
			tool = new JToolBar();
			tool.add("Login", createButton("Login"));
			tool.add("Logout", createButton("Logout"));
			tool.add("Users", createButton("Users"));
			tool.add("Add", createButton("Add Book"));
			tool.add("Query", createButton("Query Book"));
			tool.add("Modify Pwd", createButton("Modify Password"));
		}
		return tool;

	}
	
	public void setTool(){
		if(user == null){
			tool.getComponent(0).setVisible(true);
			tool.getComponent(1).setVisible(false);
			tool.getComponent(2).setVisible(false);
			tool.getComponent(3).setVisible(false);
			tool.getComponent(4).setVisible(false);
			tool.getComponent(5).setVisible(false);
		}else if(user.getIsAdmin().equals("1")){
			tool.getComponent(0).setVisible(false);
			tool.getComponent(1).setVisible(true);
			tool.getComponent(2).setVisible(true);
			tool.getComponent(3).setVisible(true);
			tool.getComponent(4).setVisible(true);
			tool.getComponent(5).setVisible(true);
		}else if(user.getIsAdmin().equals("0")){
			tool.getComponent(0).setVisible(false);
			tool.getComponent(1).setVisible(true);
			tool.getComponent(2).setVisible(false);
			tool.getComponent(3).setVisible(false);
			tool.getComponent(4).setVisible(false);
			tool.getComponent(5).setVisible(true);
		}
	}
	
	public JButton createButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(action);
		return button;
	}
	
	/*public JMenuBar createJMenuBar() {
		if (bar == null) {
			bar = new JMenuBar();

			systemJMenu = createJMenu("System");
			bookJMenu = createJMenu("Book");

			addJMenuItem(systemJMenu, "Login");
			addJMenuItem(systemJMenu, "Logout");
			systemJMenu.addSeparator();
			addJMenuItem(systemJMenu, "Exit");
			
			addJMenuItem(bookJMenu, "Add");
			addJMenuItem(bookJMenu, "Query");

			bar.add(systemJMenu);
			bar.add(bookJMenu);

		}
		return bar;

	}

	private JMenu createJMenu(String name) {
		JMenu menu = new JMenu(name);
		return menu;
	}
	
	private void addJMenuItem(JMenu menu, String name) {
		JMenuItem item = new JMenuItem(name);
		item.addActionListener(menuItemAction);
		menu.add(item);
	}*/
	
	public void addCenterCardPanel(JPanel jp) {
		FindBookPanel bookPanel = new FindBookPanel();
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
		jp.add(bookPanel, "Query Book");
		jp.add(new AddBookPanel(), "Add Book");
		jp.add(new AddUserPanel(), "Add User");
		jp.add(new ManageUserPanel(), "Users");
		
	}
	
	public void addWestCardPanel(JPanel jp) {
		jp.add(createCategoryPanel(), "Category");
		jp.add(userOptPanel(), "UserSide");		
	}
	
	public JSplitPane createSplit() {
		if (split == null) {
			split = new JSplitPane();
			split.setOneTouchExpandable(true);
			split.setLeftComponent(west);
			split.setRightComponent(center);
		}
		return split;
	}
	
	public JPanel createCategoryPanel() {
		JPanel bookTree = new JPanel();
		JPanel editButton = new JPanel();
		JPanel category = new JPanel();
		bookTree.setLayout(new BorderLayout());
		editButton.add(createButton("Edit Category"));
		category.add(categoryTree);
		categoryTree.addTreeSelectionListener(treeAction);
		bookTree.add(editButton, BorderLayout.NORTH);
		bookTree.add(category, BorderLayout.CENTER);
		if(user == null){
			bookTree.getComponent(0).setVisible(false);
		}else if(user.getIsAdmin().equals("0")){
			bookTree.getComponent(0).setVisible(false);
		}else if(user.getIsAdmin().equals("1")){
			bookTree.getComponent(0).setVisible(true);
		}				
		return bookTree;
	}

	public JPanel userOptPanel() {
		JPanel userOpt = new JPanel();
		//userOpt.setLayout(new GridLayout(2,1));
		JPanel addUser = new JPanel();
		JPanel mngUser = new JPanel();
		userOpt.setLayout(new BorderLayout());
		addUser.add(createButton("Add User"));
		mngUser.add(createButton("Manage User"));
		userOpt.add(addUser, BorderLayout.NORTH);
		userOpt.add(mngUser, BorderLayout.CENTER);
		return userOpt;
	}
	
	/*public JTree createBookTree(){
		JTree tree = new JTree();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Book Category");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Science");
		node1.add(new DefaultMutableTreeNode("Computer"));
		node1.add(new DefaultMutableTreeNode("Communication"));
		node1.add(new DefaultMutableTreeNode("Nature"));
		top.add(node1);
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("History");
		node2.add(new DefaultMutableTreeNode("America"));
		node2.add(new DefaultMutableTreeNode("World"));
		top.add(node2);
		
		tree.setModel(new DefaultTreeModel(top));
		
		tree.addTreeSelectionListener(treeAction);
		return tree;
	}*/
	
	public CardLayout getCenterCard() {
		return centerCard;
	}
	
	public CardLayout getWestCard() {
		return westCard;
	}

	public JPanel getCenter() {
		return center;
	}
	public JPanel getWest() {
		return west;
	}
	
	public CreateCategoryTree getCategoryTree() {
		return categoryTree;
	}
	
	public void setCategoryTree(CreateCategoryTree categoryTree) {
		this.categoryTree = categoryTree;
	}
	
	public void closeWindow(MainFrame jframe) {
		jframe.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				int show = JOptionPane.showConfirmDialog(null, "Are you sure to Exit?",
						"Close", JOptionPane.YES_NO_OPTION);
				if (show == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}



	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public static void main(String[] args) {
		MainFrame.getInstance();
	}
	
}
