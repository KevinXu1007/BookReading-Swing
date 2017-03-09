package edu.tamuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import edu.tamuc.bean.BookInfo;
import edu.tamuc.bean.UserInfo;
import edu.tamuc.util.ConnectionDB;

public class BookDaoImpl implements BookDao {
	
	private ConnectionDB db = ConnectionDB.getInstance();

	public void addBookInfo(BookInfo book){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "insert into book (category_id, book_name, ISBN, author, pages, publisher, language, publish_date, description, content, file_name) values (?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sb);
			ps.setInt(1, book.getCategoryId());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getAuthor());
			ps.setInt(5, book.getPages());
			ps.setString(6, book.getPublisher());
			ps.setString(7, book.getLanguage());
			ps.setString(8, book.getPublishDate());
			ps.setString(9, book.getDesc());
			ps.setString(10, book.getContent());
			ps.setString(11, book.getFileName());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addBookInfo " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}

	public Vector findBookList(){
		Vector data = new Vector();

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "select * from book ";
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("ISBN"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("publish_date"));
				
				data.add(v);
			}

		} catch (SQLException e) {
			System.out.println("findBookList " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return data;
	}
	
	public Vector findBookListByCategory(int caregoryId){
		Vector data = new Vector();

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "select * from book where category_id = " + caregoryId;
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("ISBN"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("publish_date"));
				
				data.add(v);
			}

		} catch (SQLException e) {
			System.out.println("findBookList " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return data;
	}
	
	public Vector findBookListByCon(String bookName, String ISBN){
		Vector data = new Vector();

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String str = "";
			
			if(!bookName.equals("")){
				str += " and book_name like '%" +bookName+ "%'";
			}
			
			if(!ISBN.equals("")){
				str += " and ISBN = '" +ISBN+ "'";
			}
			
			if(!bookName.equals("") || !ISBN.equals("")){
				str = " where " + str.substring(4);
			}						
			
			String sb = "select * from book " + str;
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("ISBN"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("publish_date"));
				
				data.add(v);
			}

		} catch (SQLException e) {
			System.out.println("findBookList " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return data;
	}
	
	public void delBookByISBN(String ISBN){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "delete from book where ISBN = '" + ISBN +"'";
			ps = con.prepareStatement(sb);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delBookByISBN" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	
	public void updateBookInfo(int id, BookInfo book){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "update book set category_id = ?, book_name = ?, ISBN = ?, author = ?, pages = ?, publisher = ?, language = ?, publish_date = ?, description = ?, content = ?, file_name = ? where id = " + id;
			ps = con.prepareStatement(sb);
			ps.setInt(1, book.getCategoryId());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getAuthor());
			ps.setInt(5, book.getPages());
			ps.setString(6, book.getPublisher());
			ps.setString(7, book.getLanguage());
			ps.setString(8, book.getPublishDate());
			ps.setString(9, book.getDesc());
			ps.setString(10, book.getContent());
			ps.setString(11, book.getFileName());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addBookInfo " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	
	public BookInfo findBookByISBN(String ISBN){
		BookInfo book = null;
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sb =	"select * from book where ISBN = '"+ISBN+"'";
			ps = con.prepareStatement(sb);
			rs = ps.executeQuery();
			//System.out.println(sb);
			if(rs.next()) {
				book = new BookInfo();
				book.setId(rs.getInt("id"));
				book.setBookName(rs.getString("book_name"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("author"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setLanguage(rs.getString("language"));
				book.setPublishDate(rs.getString("publish_date"));
				book.setDesc(rs.getString("description"));
				book.setContent(rs.getString("content"));
				book.setFileName(rs.getString("file_name"));
				book.setCategoryId(rs.getInt("category_id"));
			}

		} catch (SQLException e) {
			System.out.println("findBookByISBN" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		
		return book;
	}
	
	public BookInfo findBookById(int id) {
		BookInfo book = null;

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sb = "select * from book where id = " + id;
			ps = con.prepareStatement(sb);
			rs = ps.executeQuery();
			// System.out.println(sb);
			if (rs.next()) {
				book = new BookInfo();
				book.setId(rs.getInt("id"));
				book.setBookName(rs.getString("book_name"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("author"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setLanguage(rs.getString("language"));
				book.setPublishDate(rs.getString("publish_date"));
				book.setDesc(rs.getString("description"));
				book.setContent(rs.getString("content"));
				book.setFileName(rs.getString("file_name"));
				book.setCategoryId(rs.getInt("category_id"));
			}

		} catch (SQLException e) {
			System.out.println("findBookByISBN" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return book;
	}
}
