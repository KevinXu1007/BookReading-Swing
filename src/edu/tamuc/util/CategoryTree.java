package edu.tamuc.util;

import java.util.ArrayList;
import java.util.Vector;

import edu.tamuc.bean.BookCategory;
import edu.tamuc.dao.CategoryDao;
import edu.tamuc.dao.CategoryDaoImpl;

public class CategoryTree {
	
	private static Vector v;
	
	public CategoryTree(){
		
	}
	
	public Vector getCategoryList(){
		v = new Vector();
		v.add("");
		CategoryDao dao = new CategoryDaoImpl();
		ArrayList<BookCategory> list=dao.getAllCategory();  
	    list.remove(0); 
	    
	    initTree(list, 1);
	    
	    return v;
	}
	
	public static void initTree(ArrayList<BookCategory> list, int pid) {  
	    for (BookCategory category : list) {  
	        if (category.getPid() == pid) {  
	            v.add(category);  
	            initTree(list, category.getId());  
	            
	        }  
	    }  
	}  

}
