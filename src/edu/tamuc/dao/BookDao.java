package edu.tamuc.dao;

import java.util.Vector;

import edu.tamuc.bean.BookInfo;

public interface BookDao {

	public void addBookInfo(BookInfo book);

	public Vector findBookList();
	
	public Vector findBookListByCategory(int caregoryId);
	
	public Vector findBookListByCon(String bookName, String ISBN);
	
	public void delBookByISBN(String ISBN);
	
	public void updateBookInfo(int id, BookInfo book);
	
	public BookInfo findBookByISBN(String ISBN);
	
	public BookInfo findBookById(int id);
	
}
