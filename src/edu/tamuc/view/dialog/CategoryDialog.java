package edu.tamuc.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


import edu.tamuc.bean.BookCategory;
import edu.tamuc.dao.CategoryDao;
import edu.tamuc.dao.CategoryDaoImpl;
import edu.tamuc.view.frame.MainFrame;
import edu.tamuc.view.panel.CreateCategoryTree;
import edu.tamuc.view.panel.LoginPanel;

public class CategoryDialog extends JDialog {
	
	private static CategoryDialog instance;
	private CreateCategoryTree tree = new CreateCategoryTree();	
	private DefaultTreeModel model;
	private JButton addButton = new JButton("Add New Sub-Category");
	private JButton delButton = new JButton("Delete Category");
	
	public CategoryDialog(){
		init();
	}
	
	public static CategoryDialog getInstance(){
		if (instance == null) {
			instance = new CategoryDialog();
		}
		return instance;
	}
	
	CellEditorListener listener = new CellEditorListener(){
    	@Override
    	public void editingCanceled(ChangeEvent e) {
    		
        }
    	@Override
    	public void editingStopped(ChangeEvent e) {
    		DefaultMutableTreeNode selectedNode  
            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent(); 
    		selectedNode.setUserObject(tree.getCellEditor().getCellEditorValue());
        	
    		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
    		
    		int pid = ((BookCategory)parentNode.getUserObject()).getId();
    		String categoryName = selectedNode.getUserObject().toString();
    		
    		BookCategory category = new BookCategory();
    		category.setCategoryName(categoryName);
    		category.setPid(pid);
    		
    		CategoryDao dao = new CategoryDaoImpl();
    		dao.addCategory(category);
    		
    		category = dao.getCategoryByName(categoryName);
    		selectedNode.setUserObject(category);
    		
        	System.out.println("---"+((BookCategory)selectedNode.getUserObject()).getId());
        }
    };
	
	public void init() {

		this.setTitle("Edit Book Category");
		JPanel panel = new JPanel();
		JPanel optPanel = new JPanel();		
		JScrollPane JSPanel = new JScrollPane(tree);
		
		model = (DefaultTreeModel)tree.getModel(); 
		tree.setEditable(true);
		
		addButton.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                DefaultMutableTreeNode selectedNode  
                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  
                if (selectedNode == null) return;  
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Category");  
                //System.out.println(selectedNode.getLevel()+" ==== " +selectedNode.getChildCount());
                selectedNode.add(newNode);  
                TreeNode[] nodes = model.getPathToRoot(newNode);  
                TreePath path = new TreePath(nodes);  
                tree.scrollPathToVisible(path);  
                tree.updateUI();  
                tree.startEditingAtPath(path); 
                tree.getCellEditor().addCellEditorListener(listener);
            }  
        }); 
		
		delButton.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                DefaultMutableTreeNode selectedNode  
                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  
				if (selectedNode != null && selectedNode.getParent() != null) {
					int show = JOptionPane.showConfirmDialog(null,
							"Are you sure to delete this category?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (show == JOptionPane.YES_OPTION) {
						model.removeNodeFromParent(selectedNode);
						int id = ((BookCategory) selectedNode.getUserObject())
								.getId();
						CategoryDao dao = new CategoryDaoImpl();
						dao.delCategory(id);
					}
				}
            }  
        });  
		
		panel.setLayout(new BorderLayout());
		optPanel.add(addButton);
		optPanel.add(delButton);
		panel.add(JSPanel);
		panel.add(optPanel, BorderLayout.SOUTH);
		
		this.add(panel);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		closeWindow(this);
		
	}
	
	public CreateCategoryTree getCategoryTree() {
		return tree;
	}
	
	public void closeWindow(CategoryDialog jframe) {
		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {				
				MainFrame.getInstance().setCategoryTree(tree);
				MainFrame.getInstance().init();
				MainFrame.getInstance().getWestCard().show(MainFrame.getInstance().getWest(), "Category");
				CategoryDialog.getInstance().dispose();
				
			}

		});
	}


}
