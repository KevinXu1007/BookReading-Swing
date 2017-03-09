package edu.tamuc.bean;

public class UserInfo {
	private String userName;
	private String password;
	private String email;
	private String isAdmin;
	
	public UserInfo(){
		
	}
    public UserInfo(String _userName, String _email, String _isAdmin){
    	this.userName = _userName;
    	this.email = _email;
    	this.isAdmin = _isAdmin;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
