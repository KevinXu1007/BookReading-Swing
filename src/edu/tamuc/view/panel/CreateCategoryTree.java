package edu.tamuc.view.panel;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;

import edu.tamuc.action.JTreeAction;
import edu.tamuc.bean.BookCategory;
import edu.tamuc.dao.CategoryDao;
import edu.tamuc.dao.CategoryDaoImpl;

public class CreateCategoryTree extends JTree {
	
	public CreateCategoryTree(){
		
		CategoryDao dao = new CategoryDaoImpl();  
		BookCategory category = dao.getCategoryById(1);  
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(category); 
        
        ArrayList<BookCategory> list = getAllCategory();  
        initTree(list, 1, root);  
  
        DefaultTreeModel model = new DefaultTreeModel(root);  
        this.setModel(model);  
        this.getSelectionModel().setSelectionMode(  
                DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);  
       
	}	

	public ArrayList<BookCategory> getAllCategory() {  
		CategoryDao dao = new CategoryDaoImpl();
		ArrayList<BookCategory> list=dao.getAllCategory();  
	    list.remove(0);  
	    return list;  
	} 
	
	public static void initTree(ArrayList<BookCategory> list, int pid, DefaultMutableTreeNode parent) {  
	    for (BookCategory category : list) {  
	        if (category.getPid() == pid) {  
	            DefaultMutableTreeNode other = new DefaultMutableTreeNode(category);  
	            initTree(list, category.getId(), other);  
	            parent.add(other);  
	        }  
	    }  
	}  

}
