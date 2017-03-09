package edu.tamuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import edu.tamuc.bean.BookCategory;
import edu.tamuc.util.ConnectionDB;

public class CategoryDaoImpl implements CategoryDao{
	private ConnectionDB db = ConnectionDB.getInstance();
	
	public ArrayList<BookCategory> getAllCategory() {  
		ArrayList<BookCategory> list = new ArrayList<BookCategory>();

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuffer sb = new StringBuffer(
					"select * from category ");
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				BookCategory category = new BookCategory();
				category.setCategoryName(rs.getString("category_name"));
				category.setId(rs.getInt("id"));
				category.setPid(rs.getInt("parent_id"));
				list.add(category);
			}

		} catch (SQLException e) {
			System.out.println("getBookCategory" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return list;  
	} 
	
	public BookCategory getCategoryById(int id){
		BookCategory category = new BookCategory();
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuffer sb = new StringBuffer(
					"select * from category where id = " + id);
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			if (rs.next()) {				
				category.setCategoryName(rs.getString("category_name"));
				category.setId(rs.getInt("id"));
				category.setPid(rs.getInt("parent_id"));
			}

		} catch (SQLException e) {
			System.out.println("getCategoryById" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		return category;
	}
	
	public BookCategory getCategoryByName(String categoryName){
        BookCategory category = new BookCategory();
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuffer sb = new StringBuffer(
					"select * from category where category_name = '" + categoryName + "'");
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			if (rs.next()) {				
				category.setCategoryName(rs.getString("category_name"));
				category.setId(rs.getInt("id"));
				category.setPid(rs.getInt("parent_id"));
			}

		} catch (SQLException e) {
			System.out.println("getCategoryByName" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		return category;
	}
	public void addCategory(BookCategory category){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "insert into category (category_name, parent_id) values (?,?)";
			ps = con.prepareStatement(sb);
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getPid());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addCategory" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	public boolean isExistByName(String categoryName){
        boolean flag = false;
		BookCategory category = new BookCategory();
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuffer sb = new StringBuffer(
					"select * from category where category_name = '" + categoryName + "'");
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			if (rs.next()) {	
				flag = true;
				category.setCategoryName(rs.getString("category_name"));
				category.setId(rs.getInt("id"));
				category.setPid(rs.getInt("parent_id"));
			}

		} catch (SQLException e) {
			System.out.println("isExistByName" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		return flag;
	}
	
	public void delCategory(int id){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "delete from category where id = " + id;
			ps = con.prepareStatement(sb);			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delCategory" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}

}
