package edu.tamuc.dao;

import java.util.ArrayList;

import edu.tamuc.bean.BookCategory;

public interface CategoryDao {
	
	public ArrayList<BookCategory> getAllCategory();
	public BookCategory getCategoryById(int id);
	public BookCategory getCategoryByName(String categoryName);
	public void addCategory(BookCategory category);
	public boolean isExistByName(String categoryName);
	public void delCategory(int id);

}
