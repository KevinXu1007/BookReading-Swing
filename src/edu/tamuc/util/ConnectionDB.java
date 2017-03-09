package edu.tamuc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionDB {
	private static ConnectionDB instance = null;
	private String driver;
	private String url;
	private String user;
	private String pw;

	private ConnectionDB() {
		config();
	}

	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();
		}
		return instance;
	}
	
	public Connection getConnetion(){
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager
			.getConnection(url,user,pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void closeDB(Connection con ,Statement st,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void config(){
		Properties p = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("db.properties");
			p.load(in);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver = p.getProperty("driver");
		url = p.getProperty("url");
		user = p.getProperty("user");
		pw = p.getProperty("pw");
	}
	
	public static void main(String[] args) {
		ConnectionDB db = ConnectionDB.getInstance();
		db.getConnetion();
	}

}
