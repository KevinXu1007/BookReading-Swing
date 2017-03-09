package edu.tamuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import edu.tamuc.bean.UserInfo;
import edu.tamuc.util.ConnectionDB;

public class UserDaoImpl implements UserDao{
	
	private ConnectionDB db = ConnectionDB.getInstance();
	private final String PWD = "123456"; //reset password
	
	public void addUserInfo(UserInfo user){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "insert into users (user_name, password, email, isAdmin) values (?,?,?,?)";
			ps = con.prepareStatement(sb);
			ps.setString(1, user.getUserName());
			ps.setString(2, PWD);
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getIsAdmin());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addUserInfo" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		
	}

	public Vector findUserList() {
		Vector data = new Vector();

		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "select * from users ";
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("user_name"));
				v.add(rs.getString("email"));
				if(rs.getString("isAdmin").equals("1")){
					v.add("Yes");
				}else{
					v.add("No");
				}
				data.add(v);
			}

		} catch (SQLException e) {
			System.out.println("findUserList " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}

		return data;
	}
	
	public UserInfo findUserInfo(String username, String password){
		UserInfo user = null;
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sb =	"select * from users where user_name = '"+username+"' and password = '"+password+"'";
			ps = con.prepareStatement(sb);
			rs = ps.executeQuery();
			//System.out.println(password);
			if(rs.next()) {
				user = new UserInfo();
				user.setUserName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setIsAdmin(rs.getString("isAdmin"));
			}

		} catch (SQLException e) {
			System.out.println("findUserInfo" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		
		return user;
	}
	
	public UserInfo findUserByName(String username){
		UserInfo user = null;
		
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sb =	"select * from users where user_name = '"+username+"'";
			ps = con.prepareStatement(sb);
			rs = ps.executeQuery();
			//System.out.println(password);
			if(rs.next()) {
				user = new UserInfo();
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setIsAdmin(rs.getString("isAdmin"));
			}

		} catch (SQLException e) {
			System.out.println("findUserInfo" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
		
		return user;
	}
	
	public void delUserByName(String userName){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "delete from users where user_name = '" + userName +"'";
			ps = con.prepareStatement(sb);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delUserByName" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	
	public void resetPasswordByName(String userName){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "update users set password = '"+PWD+"' where user_name = '" + userName +"'";
			ps = con.prepareStatement(sb);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("resetPasswordByName " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	
	public void updatePwdByName(String userName, String password){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "update users set password = '"+password+"' where user_name = '" + userName +"'";
			ps = con.prepareStatement(sb);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("resetPasswordByName " + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}
	
	public void setAdminByName(String userName, String isOrNo){
		Connection con = db.getConnetion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sb = "update users set isAdmin = '"+ isOrNo +"' where user_name = '" + userName +"'";
			ps = con.prepareStatement(sb);
			//System.out.print(sb);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("setAdminByName" + e.getMessage());
		} finally {
			db.closeDB(con, ps, rs);
		}
	}

}
