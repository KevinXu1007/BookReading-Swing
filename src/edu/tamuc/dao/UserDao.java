package edu.tamuc.dao;

import java.util.Vector;

import edu.tamuc.bean.UserInfo;


public interface UserDao {
	
	public void addUserInfo(UserInfo user);

	public Vector findUserList();
	
	public UserInfo findUserInfo(String username, String password);
	
	public void delUserByName(String userName);
	
	public UserInfo findUserByName(String username);
	
	public void resetPasswordByName(String userName);
	
	public void setAdminByName(String userName, String isOrNo);
	
	public void updatePwdByName(String userName, String pwd);


}
